package pl.joble.domain.offer;

import pl.joble.domain.offer.dto.JobOfferDto;

class JobOfferValidator {
    public static Integer MIN_TITLE_LENGTH = 10;
    public static Integer MIN_COMPANY_LENGTH = 3;


    Boolean isFormatCorrect(JobOfferDto dto) {
        if(dto == null) return false;
        if(dto.companyName().length()<MIN_COMPANY_LENGTH
            || dto.title().length()<MIN_TITLE_LENGTH) return false;
        return true;
    }

}
