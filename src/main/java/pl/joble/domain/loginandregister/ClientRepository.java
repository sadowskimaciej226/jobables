package pl.joble.domain.loginandregister;

public interface ClientRepository {
    Client findByUsername(String username);
    Client save(Client client);
}
