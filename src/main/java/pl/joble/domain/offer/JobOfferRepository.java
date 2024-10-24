package pl.joble.domain.offer;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface JobOfferRepository extends MongoRepository<JobOffer, String> {

    Optional<JobOffer> findById(String id);


    List<JobOffer> findAll();
    Optional<JobOffer> findByUrl(String url);
}
