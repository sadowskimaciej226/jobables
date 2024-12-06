package pl.joble.infrastructure.offer.controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.index.Indexed;

record RequestJobOfferDto(@NotNull(message = "title not null")
                          @NotEmpty(message = "title not blank")
                          String title,
                          @NotNull(message = "Company name not null")
                          @NotEmpty(message = "Company name not blank")
                          String companyName,
                          @NotNull(message = "Description not null")
                          @NotEmpty(message = "Description not blank")
                          String description,
                          @NotNull(message = "Salary not null")
                          @NotEmpty(message = "Salary not blank")
                          String salary,
                          @NotNull(message = "URL not null")
                          @NotEmpty(message = "URL not blank")
                          @Indexed(unique = true)
                          String url) {
}
