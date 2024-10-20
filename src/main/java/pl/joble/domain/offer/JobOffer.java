package pl.joble.domain.offer;

import lombok.Builder;

import java.time.LocalDateTime;


@Builder
record JobOffer(String id, String title,
                String description, String companyName, Integer salary,
                String url) {
}
