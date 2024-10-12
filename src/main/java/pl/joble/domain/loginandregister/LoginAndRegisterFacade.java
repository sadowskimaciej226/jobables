package pl.joble.domain.loginandregister;

import lombok.RequiredArgsConstructor;
import pl.joble.domain.loginandregister.dto.ClientDto;

import java.util.Optional;

import static pl.joble.domain.loginandregister.ClientMapper.*;

/*
Po zapytaniu get client otrzyma swoje dane {id, name, age} jako dto
Klient otrzymuje błąd jesli użytkownik nie istnieje(pusty optional git)

 */
@RequiredArgsConstructor
public class LoginAndRegisterFacade {
    private final ClientRepository repository;
    private final IdClientGenerable generator;
    public Optional<ClientDto> findByUsername(String username){
        Client client = repository.findByUsername(username);
        throw new RuntimeException("Not implemented yet");
    }
    public Optional<ClientDto> register(ClientDto toRegister){
        Client toSave = Client.builder()
                .id(generator.generateId())
                .username(toRegister.username())
                .age(toRegister.age())
                .aboutMe(toRegister.aboutMe())
                .location(toRegister.location())
                .build();
        Client saved = repository.save(toSave);
        return Optional.of(mapToDto(saved));
    }
}
