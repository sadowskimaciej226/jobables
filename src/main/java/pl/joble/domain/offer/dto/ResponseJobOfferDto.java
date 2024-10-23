package pl.joble.domain.offer.dto;

import lombok.Builder;

@Builder
public record ResponseJobOfferDto(String title, String company, String salary, String offerUrl) {
}
