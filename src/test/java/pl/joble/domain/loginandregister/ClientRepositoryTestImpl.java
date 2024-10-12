package pl.joble.domain.loginandregister;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class ClientRepositoryTestImpl implements ClientRepository{
    Map<String, Client> clientDb = new ConcurrentHashMap<>();
    @Override
    public Client findByUsername(String username) {
        return null;
    }

    @Override
    public Client save(Client client) {
        clientDb.put(client.username(), client);
    }
}
