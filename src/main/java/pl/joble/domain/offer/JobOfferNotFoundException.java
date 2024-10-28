package pl.joble.domain.offer;

public class JobOfferNotFoundException extends RuntimeException {
    public JobOfferNotFoundException(String message) {
        super(message);
    }
}
