package pl.joble.domain.loginandregister;

public class LoginAndRegisterFacadeConfiguration {
    public LoginAndRegisterFacade createLoginAndRegisterFacade(ClientRepository clientRepository){
        return new LoginAndRegisterFacade(clientRepository);
    }
}
