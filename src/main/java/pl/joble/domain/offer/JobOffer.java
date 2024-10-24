package pl.joble.domain.offer;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@Document
public record JobOffer(
        @Id
        String id,
        String title,
        String description,
        String companyName,
        String salary,
        @Indexed(unique = true)
        String url) {
}
