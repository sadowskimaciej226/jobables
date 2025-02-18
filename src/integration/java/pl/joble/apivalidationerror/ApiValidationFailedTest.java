package pl.joble.apivalidationerror;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import pl.joble.BaseIntegrationTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ApiValidationFailedTest extends BaseIntegrationTest {

    @Container
    public static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));
    @DynamicPropertySource
    public static void propertyOverride(DynamicPropertyRegistry registry){
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
        registry.add("joboffer.http.client.config.port", () -> wireMockServer.getPort());
        registry.add("joboffer.http.client.config.uri", () -> WIRE_MOCK_HOST);
    }

    @Test
    @WithMockUser
    public void should_return_400_bad_request_and_validation_message_when_request_is_empty() throws Exception {
        //given
        //when
        ResultActions perform = mockMvc.perform(post("/offer")
                .content("""
                        {}
                        """.trim()
                ).contentType(MediaType.APPLICATION_JSON)
        );
        //then
        MvcResult mvcResult = perform.andExpect(status().isBadRequest()).andReturn();
       


    }
    @Test
    public void should_return_400_bad_request_and_validation_message_when_request_has_empty_fields() throws Exception {
        //given
        //when
        ResultActions perform = mockMvc.perform(post("/offer")
                .content("""
                        {
                        "title":,
                        "company":,
                        "salary":,
                        "offerUrl":
                    }
                        """.trim()
                ).contentType(MediaType.APPLICATION_JSON)
        );
        //then
        MvcResult mvcResult = perform.andExpect(status().isBadRequest()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();


    }
}
