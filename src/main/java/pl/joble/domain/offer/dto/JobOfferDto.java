package pl.joble.domain.offer.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record JobOfferDto(String id, String title, String description, String companyName,
                          LocalDateTime expirationDate, Integer salary,
                          String url) {
}
