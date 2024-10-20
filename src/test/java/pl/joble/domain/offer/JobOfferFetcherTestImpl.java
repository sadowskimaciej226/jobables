package pl.joble.domain.offer;

import pl.joble.domain.offer.dto.JobOfferDto;
import pl.joble.domain.offer.dto.ResponseJobOfferDto;

import java.time.LocalDateTime;
import java.util.List;

class JobOfferFetcherTestImpl implements JobOfferFetcher{
    @Override
    public List<ResponseJobOfferDto> fetchAllOffers() {
        return List.of(ResponseJobOfferDto.builder()
                        .title("title with 10 chars :)")
                        .salary(10000)
                        .company("Company")
                        .offerUrl("122")
                        .build(),
                ResponseJobOfferDto.builder()
                        .title("title with 10 chars :)")
                        .salary(10000)
                        .company("Company")
                        .offerUrl("122")
                        .build()
        );
    }
}
