package pl.joble.domain.offer;

public class BadParametersException extends RuntimeException {
    public BadParametersException(String message) {
        super(message);
    }
}
