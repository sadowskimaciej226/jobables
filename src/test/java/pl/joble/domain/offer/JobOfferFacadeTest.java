package pl.joble.domain.offer;

import org.junit.jupiter.api.Test;
import pl.joble.domain.offer.dto.JobOfferDto;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JobOfferFacadeTest {
    JobOfferFacadeConfiguration config = new JobOfferFacadeConfiguration();
    JobOfferFacade jobOfferFacade = config.createJobOfferFacade(
            new JobOfferRepositoryTestImpl(),
            new IdOfferGenerableTestImpl());

    @Test
    void should_return_dto_after_saving(){
        //given
        JobOfferDto jobOfferDto = JobOfferDto.builder()
                .title("title with 10 chars :)")
                .description("description")
                .salary(10000)
                .companyName("Company")
                .expirationDate(LocalDateTime.now().plusDays(2))
                .build();
        //when
        JobOfferDto savedJobOfferDto =  jobOfferFacade.saveOffer(jobOfferDto);
        //then
        assertThat(savedJobOfferDto).isInstanceOf(JobOfferDto.class);
        assertThat(savedJobOfferDto.title()).isEqualTo(jobOfferDto.title());
        assertThat(savedJobOfferDto.salary()).isEqualTo(jobOfferDto.salary());

    }

    @Test
    void should_throw_exception_if_expiration_date_is_in_the_past(){
        //given
        JobOfferDto jobOfferDto = JobOfferDto.builder()
                .title("title with 10 chars :)")
                .description("description")
                .salary(10000)
                .companyName("Company")
                .expirationDate(LocalDateTime.now().minusDays(1))
                .build();
        //when
        //then
        assertThrows(BadParametersException.class, () -> jobOfferFacade.saveOffer(jobOfferDto),
                ("Job offer do not accomplish requirements to be save"));
    }
    @Test
    void should_throw_exception_if_title_dosnt_at_least_have_10_chars(){
        //given
        JobOfferDto jobOfferDto = JobOfferDto.builder()
                .title("title")
                .description("description")
                .salary(10000)
                .companyName("Company")
                .expirationDate(LocalDateTime.now().plusDays(3))
                .build();
        //when
        //then
        assertThrows(BadParametersException.class, () -> jobOfferFacade.saveOffer(jobOfferDto),
                ("Job offer do not accomplish requirements to be save"));

    }
    @Test
    void should_throw_exception_if_company_dosnt_have_at_least_3_chars(){
        //given
        JobOfferDto jobOfferDto = JobOfferDto.builder()
                .title("sdfsdfsfsd")
                .description("description")
                .salary(10000)
                .companyName("qq")
                .expirationDate(LocalDateTime.now().plusDays(3))
                .build();
        //when
        //then
        assertThrows(BadParametersException.class, () -> jobOfferFacade.saveOffer(jobOfferDto),
                ("Job offer do not accomplish requirements to be save"));

    }
    @Test
    void should_throw_exception_when_toSave_has_empty_field(){
        //given
        JobOfferDto jobOfferDto = null;
        //when
        //then
        assertThrows(BadParametersException.class, () -> jobOfferFacade.saveOffer(jobOfferDto),
                ("Job offer do not accomplish requirements to be save"));
    }
    @Test
    void should_throw_exception_when_object_toSave_has_id(){
        //given
        JobOfferDto jobOfferDto = JobOfferDto.builder()
                .id("1")
                .title("sdfsdfsfsd")
                .description("description")
                .salary(10000)
                .companyName("qdfsfdq")
                .expirationDate(LocalDateTime.now().plusDays(3))
                .build();
        //when
        //then
        assertThrows(BadParametersException.class, () -> jobOfferFacade.saveOffer(jobOfferDto),
                ("Job offer do not accomplish requirements to be save"));
    }

    @Test
    void should_return_jobOffer_by_id(){
        //given
        JobOfferDto toSave = JobOfferDto.builder()
                .title("title with 10 chars :)")
                .description("description")
                .salary(10000)
                .companyName("Company")
                .expirationDate(LocalDateTime.now().plusDays(2))
                .build();
        JobOfferDto saved = jobOfferFacade.saveOffer(toSave);
        //when
        JobOfferDto foundOfferDto = jobOfferFacade.findOfferById("1");
        //then
        assertThat(saved).isEqualTo(foundOfferDto);
    }
    @Test
    void should_throw_exception_when_offer_by_id_dose_not_exist(){
        JobOfferDto toSave = JobOfferDto.builder()
                .title("title with 10 chars :)")
                .description("description")
                .salary(10000)
                .companyName("Company")
                .expirationDate(LocalDateTime.now().plusDays(2))
                .build();
        jobOfferFacade.saveOffer(toSave);
        //when
        //then
        assertThrows(JobOfferNotFoundException.class,() -> jobOfferFacade.findOfferById("2"),
                "Unabled to to find offer with such id");

    }

    @Test
    void should_return_cache_data_for_1h_after_same_first_request(){
        //given
//        JobOfferDto jobOfferDto = JobOfferDto.builder()
//                .title("title with 10 chars :)")
//                .description("description")
//                .salary(10000)
//                .companyName("Company")
//                .expirationDate(LocalDateTime.now().plusDays(2))
//                .build();
//        JobOfferDto savedJobOffer = jobOfferFacade.saveOffer(jobOfferDto);
        //when

        //then

    }
    @Test
    void should_return_OfferDtoList(){
        //given

        //when

        //then

    }
    @Test
    void should_return_empty_optional_if_offerList_is_empty(){
        //given

        //when

        //then

    }



}