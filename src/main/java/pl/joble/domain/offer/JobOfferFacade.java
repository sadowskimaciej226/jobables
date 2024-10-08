package pl.joble.domain.offer;

import lombok.RequiredArgsConstructor;
import pl.joble.domain.offer.dto.JobOfferDto;


import static pl.joble.domain.offer.JobOfferMapper.*;

//offers CRUD(rather CR than CRUD) operations with periodically fetching data
//can return all offers
//enables to save own offers to db
//enables to fetch and save data if not exists
@RequiredArgsConstructor
public class JobOfferFacade {
    private final JobOfferRepository jobOfferRepository;
    private final JobOfferValidator validator;
    private final IdOfferGenerable idGenerator;

    public JobOfferDto saveOffer(JobOfferDto dto){
        if(validator.isFormatCorrect(dto)) {
            JobOffer toSave = mapToJobOffer(dto, idGenerator);
            JobOffer saved = jobOfferRepository.save(toSave);
            return mapToDto(saved);
        }else throw new BadParametersException("Job offer do not accomplish requirements to be save");
    }

    public JobOfferDto findOfferById(String id) {
        JobOffer foundOffer = jobOfferRepository.findById(id).orElseThrow(() -> new JobOfferNotFoundException("Not found offer with id: " + id));
        return mapToDto(foundOffer);

    }
}
