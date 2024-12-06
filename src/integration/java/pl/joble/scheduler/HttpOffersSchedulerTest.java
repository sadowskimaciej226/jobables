package pl.joble.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import pl.joble.BaseIntegrationTest;
import pl.joble.JobablesSpringApplication;
import pl.joble.domain.offer.JobOfferFetcher;

import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = JobablesSpringApplication.class, properties = "scheduling.enabled=true")
public class HttpOffersSchedulerTest extends BaseIntegrationTest {

    @Container
    public static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));
    @DynamicPropertySource
    public static void propertyOverride(DynamicPropertyRegistry registry){
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
        registry.add("joboffer.http.client.config.port", () -> wireMockServer.getPort());
        registry.add("joboffer.http.client.config.uri", () -> WIRE_MOCK_HOST);
    }

    @SpyBean
    JobOfferFetcher fetcher;
    @Test
    public void should_run_http_client_offers_fetching_exactly_given_times(){

        await()
                .atMost(Duration.ofMillis(2000))
                .untilAsserted(() -> verify(fetcher, times(2)).fetchAllOffers());

    }
}
