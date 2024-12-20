package pl.joble.domain.offer.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.io.Serializable;


@Builder
public record JobOfferDto(String id,
                          String title,
                          String description,
                          String companyName,
                          String salary,
                          String url) implements Serializable {
}
