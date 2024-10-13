package pl.joble.domain.loginandregister;

import lombok.RequiredArgsConstructor;
import pl.joble.domain.loginandregister.dto.ClientDto;
import pl.joble.domain.loginandregister.dto.ClientToRegisterDto;

import java.util.Optional;

import static pl.joble.domain.loginandregister.ClientMapper.*;

@RequiredArgsConstructor
public class LoginAndRegisterFacade {
    private final ClientRepository repository;

    public Optional<ClientDto> findByUsername(String username){
        Optional<Client> client = repository.findByUsername(username);
        if(client.isEmpty()){
            return Optional.empty();
        } else{
            ClientDto foundClient = mapToDto(client.get());
            return Optional.of(foundClient);
        }
    }
    public Optional<ClientDto> register(ClientToRegisterDto toRegister){
        if(repository.findByUsername(toRegister.username()).isEmpty()) {
            Client toSave = Client.builder()

                    .username(toRegister.username())
                    .password(toRegister.password())
                    .age(toRegister.age())
                    .aboutMe(toRegister.aboutMe())
                    .location(toRegister.location())
                    .build();
            Client saved = repository.save(toSave);
            return Optional.of(mapToDto(saved));
        } else throw new ClientAlreadyExists("Client with such username already exits try with something else");
    }
}
