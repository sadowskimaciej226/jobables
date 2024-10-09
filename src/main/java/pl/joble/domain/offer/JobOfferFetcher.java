package pl.joble.domain.offer;

import pl.joble.domain.offer.dto.JobOfferDto;

import java.util.List;

public interface JobOfferFetcher {
    List<JobOfferDto> fetchAllOffers();
}
