package pl.joble.domain.offer.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;


@Builder
public record JobOfferDto(String id,
                          @NotNull(message = "title not null")
                          @NotEmpty(message = "title not blank")
                          String title,
                          @NotNull(message = "description not null")
                          @NotEmpty(message = "description not blank")
                          String description,
                          @NotNull(message = "Company name not null")
                          @NotEmpty(message = "Company name not blank")
                          String companyName,
                          @NotNull(message = "Salary not null")
                          @NotEmpty(message = "Salary not blank")
                          String salary,
                          @NotNull(message = "URL not null")
                          @NotEmpty(message = "URL not blank")
                          String url) {
}
