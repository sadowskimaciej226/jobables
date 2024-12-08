package pl.joble.infrastructure.offer.http;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import pl.joble.domain.offer.JobOfferFetcher;
import pl.joble.domain.offer.dto.ResponseJobOfferDto;

import java.util.List;

@AllArgsConstructor
@Log4j2
public class JobOfferFetcherRestTemplate implements JobOfferFetcher {

    private final RestTemplate restTemplate;
    private final String uri;
    private final int port;

    @Override
    public List<ResponseJobOfferDto> fetchAllOffers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);
        try {


            final String url = UriComponentsBuilder.fromHttpUrl(uri + ":" + port + "/offers")
                    .toUriString();

            ResponseEntity<List<ResponseJobOfferDto>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<>() {
                    });

            List<ResponseJobOfferDto> responseJobOfferDtoList = response.getBody();
            if (responseJobOfferDtoList == null) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
            return responseJobOfferDtoList;
        }catch(ResourceAccessException e){
        log.error("Error while fetching job offers numbers using http client: " + e.getMessage());
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
