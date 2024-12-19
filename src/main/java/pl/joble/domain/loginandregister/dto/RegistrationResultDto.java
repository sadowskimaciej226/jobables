package pl.joble.domain.loginandregister.dto;

import lombok.Builder;

@Builder
public record RegistrationResultDto(String id, Boolean isRegistered, String username) {
}
