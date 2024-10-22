package pl.joble.feature;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import pl.joble.BaseIntegrationTest;
import pl.joble.infrastructure.offer.http.JobOfferFetcherRestTemplate;
import pl.joble.infrastructure.offer.scheduler.FetchOffersScheduler;

import java.time.Duration;

import static org.awaitility.Awaitility.await;

public class HappyPathTestIntegration extends BaseIntegrationTest implements SampleJobOfferResponse{


    @Autowired
    FetchOffersScheduler fetchOffersScheduler;
    @Test
    public void should_user_get_offers(){
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithZeroOffersJson())));
//        1.	Scheduler runs for the 1st time and made GET to server to get All joboffers
        //given

        //when
        //fetchOffersScheduler.fetchAllOffersAndSaveIfNotExists();

        //then

//        2.	Scheduler made Get to server every 3 hours
//        4.	System validates data and return 200 OK with JWT token if validation is successful or 400 BAD REQUEST if for example there is already user with that email
//        5.	If account already exists user can POST /login with email and password to get jwt token, system can return 200OK with JWT or 400 BAD REQUEST if password is wrong
//        6.	User with JWT(and in any endpoint except /login and /singin) can GET /joboffers,
//        7.	 Job offers are saved in ConcurrentHashmap to cache data (all data are removed from hashmap every 60 min from request)
//        8.	User can save joboffer on POST /joboffer

    }

}
