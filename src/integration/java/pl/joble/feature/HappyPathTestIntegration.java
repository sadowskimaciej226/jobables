package pl.joble.feature;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.tomakehurst.wiremock.client.WireMock;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import pl.joble.BaseIntegrationTest;

import pl.joble.domain.offer.JobOfferFacade;
import pl.joble.domain.offer.dto.JobOfferDto;
import pl.joble.infrastructure.offer.scheduler.FetchOffersScheduler;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class HappyPathTestIntegration extends BaseIntegrationTest implements SampleJobOfferResponse{

    @Container
    public static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));
    @DynamicPropertySource
    public static void propertyOverride(DynamicPropertyRegistry registry){
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
        registry.add("joboffer.http.client.config.port", () -> wireMockServer.getPort());
        registry.add("joboffer.http.client.config.uri", () -> WIRE_MOCK_HOST);
    }


    @MockBean
    FetchOffersScheduler scheduler;
    @Autowired
    JobOfferFacade jobOfferFacade;

    @Test
    public void should_user_get_offers() throws Exception {
//        1. There aren't existing job offers in external server
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithZeroOffersJson())));

//       2. user tried to get JWT token by requesting POST /token with username=name, password = pass and system returned error
//       3. user made GET /offers with no jwt token and system returned UNAUTHORIZED(401)
//       4. user made POST /register with username=name, password= pass adne system registered user with status OK(200)
//       5. user tried to get JWT token by requesting POST /token with username= user, password= pass and system returned OK(200) with token


//       6.	Scheduler runs for the 1st time and made GET to server to get All joboffers
        //given
        //when
        List<JobOfferDto> fetchedWithZeroOffers = jobOfferFacade.fetchAndSaveAllOffers();
        //then
        assertThat(fetchedWithZeroOffers, is(empty()));

        ResultActions perform = mockMvc.perform(get("/offer"));

        MvcResult mvcResult = perform.andExpect(status().isOk()).andReturn();
        String jsonWithOffers = mvcResult.getResponse().getContentAsString();
        List<JobOfferDto> offers = objectMapper.readValue(jsonWithOffers, new TypeReference<>() {
        });
        assertThat(offers, is(empty()));


//       7. user made GET/offer/id and system returned Not_found
        ResultActions perform2 = mockMvc.perform(get("/offer" + "/notexistingid"));

        perform2.andExpect(status().isNotFound())
                .andExpect(content().json("""
                        {
                        "message": "Not found for id: notexistingid",
                        "status": "NOT_FOUND"
                        }
                        """.trim()
                ));


//       8. There are 2 new offers in external HTTP server
        //given
        //when
        //then
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithTwoOffersJson())));


//       9. Scheduler ran 2nd tune and made GET to external server and system added 2 new offers
        //given
        //when
        List<JobOfferDto> fetchedWithTwoOffers = jobOfferFacade.fetchAndSaveAllOffers();

        assertThat(fetchedWithTwoOffers, hasSize(2));


//       10. User made GET /offers with headed "Authorization: Bearer "AAAA.BBB.CCC" and system returned OK with 2 offers id
        //given
        //when
        ResultActions performToGetTwoOffers = mockMvc.perform(get("/offer"));

        MvcResult twoOffersResult = performToGetTwoOffers.andExpect(status().isOk()).andReturn();
        String jsonWithTwoOffers = twoOffersResult.getResponse().getContentAsString();
        List<JobOfferDto> twoJobOfferDto= objectMapper.readValue(jsonWithTwoOffers, new TypeReference<>() {
        });
        //then
        assertThat(twoJobOfferDto.get(1).title(), equalTo("Junior Java Developer"));


//       12. User made get/offers/id and system returned OK with offer
        //given
        //when
        String idOfFirstAddedOffer = twoJobOfferDto.get(1).id();
        ResultActions performToGetOneSpecificOffers = mockMvc.perform(get("/offer/" + idOfFirstAddedOffer));
        MvcResult resultWithSpecificOffer = performToGetOneSpecificOffers.andExpect(status().isOk()).andReturn();
        String jsonAsOneSpecificOffer = resultWithSpecificOffer.getResponse().getContentAsString();
        JobOfferDto specificOffer = objectMapper.readValue(jsonAsOneSpecificOffer, new TypeReference<>() {
        });
        //then
        assertThat(specificOffer.id(), equalTo(idOfFirstAddedOffer));

//       13. There are 2 new offers in external server
        //given
        //when
        //then
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithFourOffersJson())));

//       14. Scheduler ran 3rd time and made GET to external server and system added 2 new offers
        //given
        //when
        List<JobOfferDto> fetchedWithFourOffers = jobOfferFacade.fetchAndSaveAllOffers();
        //then
        assertThat(fetchedWithFourOffers, hasSize(2));


//       15. User made GET /offers with header "Authorization: AAAA>BBB>CCC" and system returned OK with 4 offers id
        ResultActions performToGetFourOffers = mockMvc.perform(get("/offer"));
        MvcResult fourOffersResult = performToGetFourOffers.andExpect(status().isOk()).andReturn();
        String jsonWithFourOffers = fourOffersResult.getResponse().getContentAsString();
        List<JobOfferDto> fourJobOfferDto= objectMapper.readValue(jsonWithFourOffers, new TypeReference<>() {
        });
        //then
        assertThat(fourJobOfferDto.get(3).title(), equalTo("Senior Java Developer"));


//        16.	User can save joboffer on POST /joboffer only with unique url
        //given
        //when
        ResultActions performOfferWithNotUniqueURl = mockMvc.perform(post("/offer")
                .content("""
                                {
                                      "title": "titlewith10chars",
                                      "companyName": "some company",
                                      "description": "some description",
                                      "salary": "10000",
                                      "url": "link "
                                    }
                """.trim()
                ).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
        String json = performOfferWithNotUniqueURl.andReturn().getResponse().getContentAsString();
        JobOfferDto jobOfferDto = objectMapper.readValue(json, JobOfferDto.class);

        //then
        assertThat(jobOfferDto.url(), equalTo("link "));
        assertThat(jobOfferDto.salary(), equalTo("10000"));


    }

}
