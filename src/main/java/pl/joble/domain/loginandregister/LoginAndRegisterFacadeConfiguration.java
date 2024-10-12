package pl.joble.domain.loginandregister;

public class LoginAndRegisterFacadeConfiguration {
    public LoginAndRegisterFacade createLoginAndRegisterFacade(ClientRepository clientRepository, IdClientGenerable generable){
        return new LoginAndRegisterFacade(clientRepository, generable);
    }
}
