package pl.joble.domain.offer;

class BadParametersException extends RuntimeException {
    public BadParametersException(String message) {
        super(message);
    }
}
