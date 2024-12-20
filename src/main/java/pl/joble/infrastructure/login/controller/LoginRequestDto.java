package pl.joble.infrastructure.login.controller;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank(message = "Username can't be blank")
        String username,
        @NotBlank(message = "Password can't be blank")
        String password
) {
}
