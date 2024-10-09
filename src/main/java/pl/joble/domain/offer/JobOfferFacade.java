package pl.joble.domain.offer;

import lombok.RequiredArgsConstructor;
import pl.joble.domain.offer.dto.JobOfferDto;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final JobOfferFetcher client;

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
    public List<JobOfferDto> findAllOffer(){
        List<JobOffer> jobOffers = jobOfferRepository.findAll();
        return jobOffers.stream()
                .map(JobOfferMapper::mapToDto)
                .toList();
    }
    private Optional<JobOfferDto> findByTitleAndCompanyName(String title, String companyName){
        Optional<JobOffer> jobOffer = jobOfferRepository.findByTitleAndCompanyName(title,companyName);
        if(jobOffer.isEmpty()) return Optional.empty();
        return Optional.of(mapToDto(jobOffer.get()));
    }
    public List<JobOfferDto> fetchAndSaveAllOffers(){
        List<JobOfferDto> fetchOffers = client.fetchAllOffers();

        List<JobOfferDto> fetchedToSave = fetchOffers.stream()
                .filter(dto -> findByTitleAndCompanyName(dto.title(), dto.companyName()).isEmpty())
                .toList();

        return fetchedToSave.stream()
                .map(dto -> saveOffer(dto))
                .toList();


    }


}



