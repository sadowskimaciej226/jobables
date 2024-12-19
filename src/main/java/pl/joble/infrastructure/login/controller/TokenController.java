package pl.joble.infrastructure.login.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.joble.infrastructure.security.JwtAuthenticatorFacade;

@RestController
@RequiredArgsConstructor
class TokenController {

    private final JwtAuthenticatorFacade jwtAuthenticatorFacade;
    @PostMapping("/token")
    public ResponseEntity<JwtResponse> authenticationAndGenerateToken(@Valid @RequestBody LoginRequestDto loginRequestDto){
        final JwtResponse jwtResponseDto = jwtAuthenticatorFacade.authenticateAndGenerateToken(loginRequestDto);
        return ResponseEntity.ok(jwtResponseDto);
    }
}
