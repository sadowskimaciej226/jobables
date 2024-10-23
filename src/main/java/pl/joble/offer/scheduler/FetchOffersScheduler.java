package pl.joble.offer.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.joble.domain.offer.JobOfferFacade;
import pl.joble.domain.offer.dto.JobOfferDto;

import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class FetchOffersScheduler {

    private final JobOfferFacade jobOfferFacade;

    @Scheduled(cron = "${http.offers.scheduler.request.delay}")
    public List<JobOfferDto> fetchAllOffersAndSaveIfNotExists(){
        log.info("Fetching offers from external server started");
         return jobOfferFacade.fetchAndSaveAllOffers();
    }
}
