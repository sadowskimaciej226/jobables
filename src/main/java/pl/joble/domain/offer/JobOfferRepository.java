package pl.joble.domain.offer;

import java.util.Optional;
import java.util.UUID;

public interface JobOfferRepository {
    JobOffer save(JobOffer toSave);

    Optional<JobOffer> findById(String id);
}
