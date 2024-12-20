package pl.joble.cache.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import pl.joble.BaseIntegrationTest;
import pl.joble.domain.loginandregister.dto.RegistrationResultDto;
import pl.joble.domain.offer.JobOfferFacade;
import pl.joble.infrastructure.login.controller.JwtResponse;

import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RedisOffersCacheIntegrationTest extends BaseIntegrationTest {

    @SpyBean
    JobOfferFacade offerFacade;
    @Autowired
    CacheManager cacheManager;

    @Container
    private static final GenericContainer<?> REDIS;
    @Container
    public static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    static {
        REDIS = new GenericContainer<>("redis").withExposedPorts(6379);
        REDIS.start();
    }
    @DynamicPropertySource
    public static void propertyOverride(DynamicPropertyRegistry registry){
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
        registry.add("spring.data.redis.port", () -> REDIS.getFirstMappedPort().toString());
        registry.add("spring.cache.type", () -> "redis");
        registry.add("spring.cache.redis.time-to-live", () -> "PT1S");

    }

    @Test
    public void should_save_offer_to_cache_and_then_invalidate_by_time_to_live() throws Exception {
        ResultActions performRegister = mockMvc.perform(post("/register")
                .content("""
                        {
                        "username": "name",
                        "password": "pass"
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        ResultActions successLoginPerform = mockMvc.perform(post("/token")
                .content("""
                        {
                        "username": "name",
                        "password": "pass"
                        }
                        """.trim())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MvcResult mvcResult2 = successLoginPerform.andExpect(status().isOk()).andReturn();
        String contentAsString1 = mvcResult2.getResponse().getContentAsString();
        JwtResponse jwtResponse = objectMapper.readValue(contentAsString1, JwtResponse.class);
        String token = jwtResponse.token();

        ResultActions performToGetTwoOffers = mockMvc.perform(get("/offer")
                .header("Authorization", "Bearer " + token));

        verify(offerFacade, times(1)).findAllOffer();
        assertThat(cacheManager.getCacheNames().contains("jobOffers"), equalTo(true));

        await()
                .atMost(Duration.ofSeconds(4))
                .pollInterval(Duration.ofSeconds(1))
                .untilAsserted(() ->{
                    mockMvc.perform(get("/offer")
                            .header("Authorization", "Bearer " + token));
                    verify(offerFacade, atLeast(2)).findAllOffer();
                });
    }
}
