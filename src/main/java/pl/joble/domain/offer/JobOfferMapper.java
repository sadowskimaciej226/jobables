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
                .expirationDate(dto.expirationDate())
                .salary(dto.salary())
                .build();
    }
    public static JobOfferDto mapToDto(JobOffer jobOffer){
        return JobOfferDto.builder()
                .id(jobOffer.id())
                .title(jobOffer.title())
                .companyName(jobOffer.companyName())
                .description(jobOffer.description())
                .expirationDate(jobOffer.expirationDate())
                .salary(jobOffer.salary())
                .build();
    }
}
