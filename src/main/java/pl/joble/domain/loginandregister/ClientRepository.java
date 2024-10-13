package pl.joble.domain.loginandregister;

import java.util.Optional;

public interface ClientRepository {
    Optional<Client> findByUsername(String username);
    Client save(Client client);
}
