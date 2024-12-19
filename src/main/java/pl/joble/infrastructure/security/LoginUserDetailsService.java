package pl.joble.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.joble.domain.loginandregister.LoginAndRegisterFacade;
import pl.joble.domain.loginandregister.dto.ClientDto;

import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
class LoginUserDetailsService implements UserDetailsService {

    private final LoginAndRegisterFacade loginAndRegisterFacade;
    @Override
    public UserDetails loadUserByUsername(String username) throws BadCredentialsException {
        ClientDto byUsername = loginAndRegisterFacade.findByUsername(username).orElseThrow();
        return getUser(byUsername);
    }
    private org.springframework.security.core.userdetails.User getUser(ClientDto clientDto){
        return new org.springframework.security.core.userdetails.User(
                clientDto.username(),
                clientDto.password(),
                Collections.emptyList()
        );
    }
}
