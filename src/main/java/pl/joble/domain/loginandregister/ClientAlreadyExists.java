package pl.joble.domain.loginandregister;

class ClientAlreadyExists extends RuntimeException{
    public ClientAlreadyExists(String message) {
        super(message);
    }
}
