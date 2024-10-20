package pl.joble.domain.offer;

import org.junit.jupiter.api.Test;
import pl.joble.domain.offer.dto.JobOfferDto;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JobOfferFacadeTest {
    JobOfferFacadeConfiguration config = new JobOfferFacadeConfiguration();
    JobOfferFacade jobOfferFacade = config.createJobOfferFacade(
            new JobOfferRepositoryTestImpl(),
            new IdOfferGenerableTestImpl(),
            new JobOfferFetcherTestImpl());


    @Test
    void should_return_dto_after_saving(){
        //given
        JobOfferDto jobOfferDto = JobOfferDto.builder()
                .title("title with 10 chars :)")
                .description("description")
                .salary(10000)
                .companyName("Company")
                .build();
        //when
        JobOfferDto savedJobOfferDto =  jobOfferFacade.saveOffer(jobOfferDto);
        //then
        assertThat(savedJobOfferDto).isInstanceOf(JobOfferDto.class);
        assertThat(savedJobOfferDto.title()).isEqualTo(jobOfferDto.title());
        assertThat(savedJobOfferDto.salary()).isEqualTo(jobOfferDto.salary());

    }
    @Test
    void should_override_id_when_during_saving_dto_has_it(){
        JobOfferDto jobOfferDto = JobOfferDto.builder()
                .id("12312312")
                .title("title with 10 chars :)")
                .description("description")
                .salary(10000)
                .companyName("Company")
                .build();
        //when
        JobOfferDto savedJobOfferDto =  jobOfferFacade.saveOffer(jobOfferDto);
        //then
        assertThat(savedJobOfferDto.id()).isNotEqualTo(jobOfferDto.id());
    }


    @Test
    void should_throw_exception_if_title_dosnt_at_least_have_10_chars(){
        //given
        JobOfferDto jobOfferDto = JobOfferDto.builder()
                .title("title")
                .description("description")
                .salary(10000)
                .companyName("Company")
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
    void should_return_jobOffer_by_id(){
        //given
        JobOfferDto toSave = JobOfferDto.builder()
                .title("title with 10 chars :)")
                .description("description")
                .salary(10000)
                .companyName("Company")
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
                .build();
        jobOfferFacade.saveOffer(toSave);
        //when
        //then
        assertThrows(JobOfferNotFoundException.class,() -> jobOfferFacade.findOfferById("2"),
                "Unabled to to find offer with such id");

    }
    @Test
    void should_return_OfferDtoList(){
        //given
        JobOfferDto toSave1 = JobOfferDto.builder()
                .title("title with 10 chars :)")
                .description("description")
                .salary(10000)
                .companyName("Company")
                .build();
        JobOfferDto toSave2 = JobOfferDto.builder()
                .title("title with 10 chars :)")
                .description("description")
                .salary(10000)
                .companyName("Company")
                .build();
        jobOfferFacade.saveOffer(toSave1);
        jobOfferFacade.saveOffer(toSave2);
        //when
        List<JobOfferDto> allOffer = jobOfferFacade.findAllOffer();
        //then
        org.assertj.core.api.Assertions.assertThat(allOffer).hasSize(2);
    }
    @Test
    void should_save_all_unique_fetch_offers(){
        //given
        JobOfferDto toSave1 = JobOfferDto.builder()
                .title("title with 10 chars :)")
                .description("description")
                .salary(10000)
                .companyName("TOSAVE1")
                .url("123")
                .build();
        JobOfferDto toSave2 = JobOfferDto.builder()
                .title("title with 10 chars :)")
                .description("description")
                .salary(10000)
                .companyName("TOSAVE2")
                .url("1423")
                .build();
        jobOfferFacade.saveOffer(toSave1);
        jobOfferFacade.saveOffer(toSave2);
        //when
        List<JobOfferDto> fetchedOffers = jobOfferFacade.fetchAndSaveAllOffers();
        //then
        assertThat(fetchedOffers.get(0).url()).isNotEqualTo(toSave2.url());
        org.assertj.core.api.Assertions.assertThat(jobOfferFacade.findAllOffer()).hasSize(3);

    }


}