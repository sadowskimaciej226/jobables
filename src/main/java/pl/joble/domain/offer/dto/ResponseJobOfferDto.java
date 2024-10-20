package pl.joble.domain.offer.dto;

import lombok.Builder;

@Builder
public record ResponseJobOfferDto(String title, String company, Integer salary, String offerUrl) {
}
