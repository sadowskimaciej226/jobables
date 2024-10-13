package pl.joble.domain.loginandregister;

import org.junit.jupiter.api.Test;
import pl.joble.domain.loginandregister.dto.ClientDto;
import pl.joble.domain.loginandregister.dto.ClientToRegisterDto;


import java.util.NoSuchElementException;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LoginAndRegisterFacadeTest {
    LoginAndRegisterFacadeConfiguration config = new LoginAndRegisterFacadeConfiguration();
    LoginAndRegisterFacade loginAndRegisterFacade = config.createLoginAndRegisterFacade(
            new ClientRepositoryTestImpl(),
            new IDClientGenerableTestImpl()
    );

    @Test
    void should_save_client_with_id_to_database_if_username_is_unique(){
        //given
        ClientToRegisterDto toSave1 = ClientToRegisterDto.builder()
                .username("Tom Finger")
                .password("somepassword123")
                .age(123)
                .aboutMe("There is something about me :P")
                .location("London")
                .build();
        //when
        ClientDto registered1 = loginAndRegisterFacade.register(toSave1).orElseThrow();
        //then
        assertThat(toSave1.username()).isEqualTo(registered1.username());
        assertThat(registered1.id()).isNotNull();
    }
    @Test
    void should_throw_exception_if_username_is_already_used(){
        //given
        ClientToRegisterDto toSave1 = ClientToRegisterDto.builder()
                .username("Tom Finger")
                .password("somepassword123")
                .age(123)
                .aboutMe("There is something about me :P")
                .location("London")
                .build();
         loginAndRegisterFacade.register(toSave1).orElseThrow();
        //when
        //then
        assertThrows(ClientAlreadyExists.class, () -> loginAndRegisterFacade.register(toSave1).orElseThrow());
    }

    @Test
    void should_return_client_dto_if_user_with_such_username_exits(){
        //given
        ClientToRegisterDto toSave1 = ClientToRegisterDto.builder()
                .username("Tom Finger")
                .age(123)
                .aboutMe("There is something about me :P")
                .location("London")
                .build();
        ClientDto registered1 = loginAndRegisterFacade.register(toSave1).orElseThrow();
        //when
        ClientDto tomFinger = loginAndRegisterFacade.findByUsername("Tom Finger").orElseThrow();
        //then
        assertThat(registered1).isEqualTo(tomFinger);
    }
    @Test
    void should_throw_exception_if_user_with_such_username_not_exits(){
        //given
        ClientToRegisterDto toSave1 = ClientToRegisterDto.builder()
                .username("Tom Finger")
                .age(123)
                .aboutMe("There is something about me :P")
                .location("London")
                .build();
        loginAndRegisterFacade.register(toSave1).orElseThrow();
        //when
        //then
        assertThrows(NoSuchElementException.class,() -> loginAndRegisterFacade.findByUsername("Other username").orElseThrow());
    }

}