package pl.joble.domain.offer;

import java.util.List;
import java.util.Optional;

public interface JobOfferRepository {
    JobOffer save(JobOffer toSave);

    Optional<JobOffer> findById(String id);


    List<JobOffer> findAll();
    Optional<JobOffer> findByUrl(String url);
}
