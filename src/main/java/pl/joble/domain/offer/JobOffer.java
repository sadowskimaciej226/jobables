package pl.joble.domain.offer;

import lombok.Builder;


@Builder
record JobOffer(String id, String title,
                String description, String companyName, String salary,
                String url) {
}
