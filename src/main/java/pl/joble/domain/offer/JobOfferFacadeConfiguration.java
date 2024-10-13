package pl.joble.domain.offer;

public class JobOfferFacadeConfiguration {
    JobOfferFacade createJobOfferFacade(JobOfferRepository repo,
                                        IdOfferGenerable generator,
                                        JobOfferFetcher fetcher){
        JobOfferValidator validator = new JobOfferValidator();
        return new JobOfferFacade(repo, validator, generator, fetcher);
    }
}
