package pl.joble.domain.loginandregister;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import pl.joble.domain.loginandregister.dto.ClientDto;
import pl.joble.domain.loginandregister.dto.ClientToRegisterDto;
import pl.joble.domain.loginandregister.dto.RegistrationResultDto;

import java.util.Optional;

import static pl.joble.domain.loginandregister.ClientMapper.*;

@RequiredArgsConstructor
public class LoginAndRegisterFacade {
    public static final String BAD_CREDENTIALS = "Bad Credentials";
    private final ClientRepository repository;

    public Optional<ClientDto> findByUsername(String username){
        Optional<Client> client = repository.findByUsername(username);
        if(client.isEmpty()){
            throw new BadCredentialsException(BAD_CREDENTIALS);
        } else{
            ClientDto foundClient = mapToDto(client.get());
            return Optional.of(foundClient);
        }
    }
    public Optional<RegistrationResultDto> register(ClientToRegisterDto toRegister){
            Client toSave = Client.builder()
                    .username(toRegister.username())
                    .password(toRegister.password())
                    .age(toRegister.age())
                    .aboutMe(toRegister.aboutMe())
                    .location(toRegister.location())
                    .build();
            Client saved = repository.save(toSave);
            return Optional.of(
                    RegistrationResultDto.builder().id(saved.id())
                    .isRegistered(true)
                    .username(saved.username())
                    .build());
    }
}
