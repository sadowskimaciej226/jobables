package pl.joble.domain.offer;

import pl.joble.domain.offer.dto.JobOfferDto;

import java.time.LocalDateTime;
import java.util.List;

class JobOfferFetcherTestImpl implements JobOfferFetcher{
    @Override
    public List<JobOfferDto> fetchAllOffers() {
        return List.of(JobOfferDto.builder()
                        .title("title with 10 chars :)")
                        .description("description")
                        .salary(10000)
                        .companyName("Company")
                        .expirationDate(LocalDateTime.now().plusDays(2))
                        .build(),

                JobOfferDto.builder()
                        .title("other title with 10 chars")
                        .description("description")
                        .salary(10000)
                        .companyName("Different Company")
                        .expirationDate(LocalDateTime.now().plusDays(2))
                        .build(),
                JobOfferDto.builder()
                        .title("other title with 10 chars")
                        .description("description")
                        .salary(10000)
                        .companyName("Different Company")
                        .expirationDate(LocalDateTime.now().plusDays(2))
                        .build()
        );
    }
}
