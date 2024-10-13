package pl.joble.domain.loginandregister;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class ClientRepositoryTestImpl implements ClientRepository{
    Map<String, Client> clientDb = new ConcurrentHashMap<>();
    @Override
    public Optional<Client> findByUsername(String username) {
        try {
            return Optional.of(clientDb.get(username));
        } catch (NullPointerException e){
            return Optional.empty();
        }
    }

    @Override
    public Client save(Client client) {
        clientDb.put(client.username(), client);
        return client;
    }
}
