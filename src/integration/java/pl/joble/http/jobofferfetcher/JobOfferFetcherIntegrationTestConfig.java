package pl.joble.http.jobofferfetcher;

import org.springframework.web.client.RestTemplate;
import pl.joble.infrastructure.offer.http.JobOfferFetcherConfig;
import pl.joble.infrastructure.offer.http.JobOfferFetcherRestTemplate;

class JobOfferFetcherIntegrationTestConfig extends JobOfferFetcherConfig {
    public static final String CONTENT_TYPE_HEADER_KEY = "Content-Type";
    public static final String APPLICATION_JSON_CONTENT_TYPE_VALUE = "application/json";


    JobOfferFetcherRestTemplate remoteJobOfferClient(int port){
        RestTemplate restTemplate = restTemplate(restTemplateResponseErrorHandler());
        return remoteJobOfferClient(restTemplate, "http://localhost", port);
    }

}
