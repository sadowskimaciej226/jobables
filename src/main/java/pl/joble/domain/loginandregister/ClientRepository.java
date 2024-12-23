package pl.joble.domain.loginandregister;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface ClientRepository extends MongoRepository<Client, String> {
    Optional<Client> findByUsername(String username);

}
