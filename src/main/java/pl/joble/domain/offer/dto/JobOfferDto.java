package pl.joble.domain.offer.dto;

import lombok.Builder;


@Builder
public record JobOfferDto(String id, String title, String description, String companyName,
                          String salary,
                          String url) {
}
