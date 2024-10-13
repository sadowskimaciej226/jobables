package pl.joble.domain.loginandregister;

class ClientAlreadyExistsException extends RuntimeException{
    public ClientAlreadyExistsException(String message) {
        super(message);
    }
}
