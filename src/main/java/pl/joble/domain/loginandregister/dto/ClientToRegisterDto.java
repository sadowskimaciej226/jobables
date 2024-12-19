package pl.joble.domain.loginandregister.dto;

import lombok.Builder;

@Builder
public record ClientToRegisterDto( String username,String password, String aboutMe, Integer age, String location) {
}
