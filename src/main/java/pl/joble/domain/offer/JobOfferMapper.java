package pl.joble.domain.offer;

import pl.joble.domain.offer.dto.JobOfferDto;

class JobOfferMapper {
    private JobOfferMapper(){}

    public static JobOffer mapToJobOffer(JobOfferDto dto, IdOfferGenerable generator){
        return JobOffer.builder()
                .id(generator.generateId())
                .title(dto.title())
                .companyName(dto.companyName())
                .description(dto.description())
                .salary(dto.salary())
                .url(dto.url())
                .build();
    }
    public static JobOfferDto mapToDto(JobOffer jobOffer){
        return JobOfferDto.builder()
                .id(jobOffer.id())
                .title(jobOffer.title())
                .companyName(jobOffer.companyName())
                .description(jobOffer.description())
                .salary(jobOffer.salary())
                .url(jobOffer.url())
                .build();
    }
}
