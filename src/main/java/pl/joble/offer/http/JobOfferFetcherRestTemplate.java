package pl.joble.offer.http;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.joble.domain.offer.JobOfferFetcher;
import pl.joble.domain.offer.dto.ResponseJobOfferDto;

import java.util.List;

@AllArgsConstructor
public class JobOfferFetcherRestTemplate implements JobOfferFetcher {

    private final RestTemplate restTemplate;
    private final String uri;
    private final int port;

    @Override
    public List<ResponseJobOfferDto> fetchAllOffers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);
        final String url = UriComponentsBuilder.fromHttpUrl(uri + ":" + port + "/offers")
                .toUriString();
        ResponseEntity<List<ResponseJobOfferDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }
}
