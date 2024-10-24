package pl.joble.domain.offer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@RequiredArgsConstructor
class JobOfferRepositoryTestImpl implements JobOfferRepository {
    Map<String,JobOffer> jobOfferDb = new ConcurrentHashMap<>();
    private final IdOfferGenerableTestImpl generator = new IdOfferGenerableTestImpl();



    @Override
    public Optional<JobOffer> findById(String id) {
        try {
            return Optional.of(jobOfferDb.get(id));
        }catch (NullPointerException e){
            throw new JobOfferNotFoundException("Not found offer with id: " + id);
        }
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public <S extends JobOffer> S save(S entity) {
        JobOffer job = JobOffer.builder()
                .id(generator.generateId())
                .title(entity.title())
                .description(entity.description())
                .companyName(entity.companyName())
                .url(entity.url())
                .salary(entity.salary())
                .build();
        jobOfferDb.put(job.id(), job);
        return (S)job;
    }

    @Override
    public <S extends JobOffer> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public List<JobOffer> findAll() {
        return jobOfferDb.values()
                .stream()
                .toList();
    }

    @Override
    public List<JobOffer> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(JobOffer entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends JobOffer> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<JobOffer> findByUrl(String url) {
            return jobOfferDb.values()
                    .stream()
                    .filter(jobOffer -> jobOffer.url().equals(url) )
                    .findAny();
    }


    @Override
    public <S extends JobOffer> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends JobOffer> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends JobOffer> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends JobOffer> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends JobOffer> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends JobOffer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends JobOffer> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends JobOffer> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends JobOffer, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<JobOffer> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<JobOffer> findAll(Pageable pageable) {
        return null;
    }
}
