package pl.joble.domain.offer.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;


@Builder
public record JobOfferDto(String id,
                          String title,
                          String description,
                          String companyName,
                          String salary,
                          String url) {
}
