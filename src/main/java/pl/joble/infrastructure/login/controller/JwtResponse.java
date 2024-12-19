package pl.joble.infrastructure.login.controller;

import lombok.Builder;

@Builder
public record JwtResponse(String username,
                    String token) {
}
