package pl.joble.domain.offer;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class JobOfferRepositoryTestImpl implements JobOfferRepository {
    Map<String,JobOffer> jobOfferDb = new ConcurrentHashMap<>();

    @Override
    public Optional<JobOffer> findById(String id) {
        try {
            return Optional.of(jobOfferDb.get(id));
        }catch (NullPointerException e){
            throw new JobOfferNotFoundException("Not found offer with id: " + id);
        }
    }

    @Override
    public JobOffer save(JobOffer toSave) {
        jobOfferDb.put(toSave.id(),toSave);
        return toSave;
    }

}
