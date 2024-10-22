package pl.joble.domain.offer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class JobOfferFacadeConfiguration {

    @Bean
    JobOfferRepository jobOfferRepository(){
        return new JobOfferRepository() {
            @Override
            public JobOffer save(JobOffer toSave) {
                return null;
            }

            @Override
            public Optional<JobOffer> findById(String id) {
                return Optional.empty();
            }

            @Override
            public List<JobOffer> findAll() {
                return null;
            }

            @Override
            public Optional<JobOffer> findByUrl(String url) {
                return Optional.empty();
            }
        };
    }


    @Bean
    public JobOfferFacade jobOfferFacade(JobOfferRepository repo,
                                         JobOfferFetcher fetcher){
        JobOfferValidator validator = new JobOfferValidator();
        IdOfferGenerable generator = new IdOfferGenerator();
        return new JobOfferFacade(repo, validator, generator, fetcher);
    }
    public JobOfferFacade createJobOfferFacadeForTest(JobOfferRepository repo,
                                                      IdOfferGenerable generator,
                                                      JobOfferFetcher fetcher){
        JobOfferValidator validator = new JobOfferValidator();
        return new JobOfferFacade(repo, validator, generator, fetcher);
    }

}
