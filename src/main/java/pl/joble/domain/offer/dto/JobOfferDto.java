package pl.joble.domain.offer.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record JobOfferDto(String id, String title, String description, String companyName, LocalDateTime expirationDate, Integer salary) {
}
