package pl.joble.domain.loginandregister;

import pl.joble.domain.loginandregister.dto.ClientDto;
import pl.joble.domain.loginandregister.dto.ClientToRegisterDto;

class ClientMapper {
    private ClientMapper(){}

    public static ClientDto mapToDto(Client client){
        return ClientDto.builder()
                .id(client.id())
                .username(client.username())
                .age(client.age())
                .aboutMe(client.aboutMe())
                .location(client.location())
                .build();
    }
}
