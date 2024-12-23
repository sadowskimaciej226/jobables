package pl.joble.infrastructure.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class JobOfferFetcherConfig {
    @Bean
    public RestTemplateResponseErrorHandler restTemplateResponseErrorHandler() {
        return new RestTemplateResponseErrorHandler();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateResponseErrorHandler restTemplateResponseErrorHandler) {
        return new RestTemplateBuilder()
                .errorHandler(restTemplateResponseErrorHandler)
                .setConnectTimeout(Duration.ofMillis(1000))
                .setReadTimeout(Duration.ofMillis(1000))
                .build();
    }

    @Bean
    public JobOfferFetcherRestTemplate remoteJobOfferClient(RestTemplate restTemplate,
                                                            @Value("${joboffer.http.client.config.uri}") String uri,
                                                            @Value("${joboffer.http.client.config.port}") int port) {
        return new JobOfferFetcherRestTemplate(restTemplate, uri, port);
    }
}
