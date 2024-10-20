package pl.joble.domain.offer;

import lombok.RequiredArgsConstructor;
import pl.joble.domain.offer.dto.JobOfferDto;
import pl.joble.domain.offer.dto.ResponseJobOfferDto;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private Optional<JobOfferDto> findByUrl(String url){
        Optional<JobOffer> jobOffer = jobOfferRepository.findByUrl(url);
        if(jobOffer.isEmpty()) return Optional.empty();
        return Optional.of(mapToDto(jobOffer.get()));
    }
    public List<JobOfferDto> fetchAndSaveAllOffers(){
        List<ResponseJobOfferDto> fetchOffersResponse = client.fetchAllOffers();

        List<JobOfferDto> fetchOffers = fetchOffersResponse.stream()
                .map(dto -> {
                    return JobOfferDto.builder()
                            .title(dto.title())
                            .companyName(dto.company())
                            .salary(dto.salary())
                            .url(dto.offerUrl())
                            .build();
                }).toList();

        Set<JobOfferDto> fetchedToSave = fetchOffers.stream()
                .filter(dto -> findByUrl(dto.url()).isEmpty())
                .collect(Collectors.toSet());

        return fetchedToSave.stream()
                .map(dto -> saveOffer(dto))
                .toList();


    }


}



