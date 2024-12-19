package pl.joble.infrastructure.login.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.joble.domain.loginandregister.LoginAndRegisterFacade;
import pl.joble.domain.loginandregister.dto.ClientToRegisterDto;
import pl.joble.domain.loginandregister.dto.RegistrationResultDto;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
class RegisterController {

    private final LoginAndRegisterFacade loginAndRegisterFacade;
    private final PasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResultDto> register(@RequestBody ClientToRegisterDto registerClientDto) {
        String encodedPassword = bCryptPasswordEncoder.encode(registerClientDto.password());
        ClientToRegisterDto clientToRegisterDto = ClientToRegisterDto.builder()
                .password(encodedPassword)
                .age(registerClientDto.age())
                .aboutMe(registerClientDto.aboutMe())
                .location(registerClientDto.location())
                .username(registerClientDto.username())
                .build();
        Optional<RegistrationResultDto> register = loginAndRegisterFacade.register(clientToRegisterDto);
        return register.map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());

    }
}
