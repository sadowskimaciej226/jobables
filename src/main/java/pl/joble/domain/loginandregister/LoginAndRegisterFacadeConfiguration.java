package pl.joble.domain.loginandregister;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginAndRegisterFacadeConfiguration {
    @Bean
    public LoginAndRegisterFacade createLoginAndRegisterFacade(ClientRepository clientRepository){
        return new LoginAndRegisterFacade(clientRepository);
    }
}
