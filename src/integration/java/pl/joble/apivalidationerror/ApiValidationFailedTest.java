package pl.joble.apivalidationerror;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.joble.BaseIntegrationTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ApiValidationFailedTest extends BaseIntegrationTest {

    @Test
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
