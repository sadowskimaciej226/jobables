package pl.joble.domain.loginandregister;

import org.junit.jupiter.api.Test;
import pl.joble.domain.loginandregister.dto.ClientDto;
import pl.joble.domain.offer.JobOfferFacade;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LoginAndRegisterFacadeTest {
    LoginAndRegisterFacadeConfiguration config = new LoginAndRegisterFacadeConfiguration();
    LoginAndRegisterFacade loginAndRegisterFacade = config.createLoginAndRegisterFacade(
            new ClientRepositoryTestImpl(),
            new IDClientGenerableTestImpl()
    );

    @Test
    void should_save_client_to_database_if_username_is_unique(){
        //given
        ClientDto toSave1 = ClientDto.builder()
                .username("Tom Finger")
                .age(123)
                .aboutMe("There is something about me :P")
                .location("London")
                .build();
        ClientDto toSave2 = ClientDto.builder()
                .username("Tom Finger")
                .age(123)
                .aboutMe("There is something about me :P")
                .location("London")
                .build();
        //when
        Optional<ClientDto> registered1 = loginAndRegisterFacade.register(toSave1);
        Optional<ClientDto> registered2 = loginAndRegisterFacade.register(toSave1);
        //then


    }

}