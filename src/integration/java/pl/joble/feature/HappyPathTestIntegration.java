package pl.joble.feature;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.joble.BaseIntegrationTest;

import pl.joble.domain.offer.JobOfferFacade;
import pl.joble.domain.offer.dto.JobOfferDto;
import pl.joble.infrastructure.offer.scheduler.FetchOffersScheduler;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class HappyPathTestIntegration extends BaseIntegrationTest implements SampleJobOfferResponse{


    @MockBean
    FetchOffersScheduler scheduler;
    @Autowired
    JobOfferFacade jobOfferFacade;
    @Test
    public void should_user_get_offers() throws Exception {
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithZeroOffersJson())));
//        1.	Scheduler runs for the 1st time and made GET to server to get All joboffers
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


//        4.	System validates data and return 200 OK with JWT token if validation is successful or 400 BAD REQUEST if for example there is already user with that email
//        5.	If account already exists user can POST /login with email and password to get jwt token, system can return 200OK with JWT or 400 BAD REQUEST if password is wrong
//        6.	User with JWT(and in any endpoint except /login and /singin) can GET /joboffers,
//        7.	 Job offers are saved in ConcurrentHashmap to cache data (all data are removed from hashmap every 60 min from request)
//        8.	User can save joboffer on POST /joboffer

    }

}
