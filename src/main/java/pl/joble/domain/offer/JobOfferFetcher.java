package pl.joble.domain.offer;

import pl.joble.domain.offer.dto.JobOfferDto;
import pl.joble.domain.offer.dto.ResponseJobOfferDto;

import java.util.List;

public interface JobOfferFetcher {
    List<ResponseJobOfferDto> fetchAllOffers();
}
