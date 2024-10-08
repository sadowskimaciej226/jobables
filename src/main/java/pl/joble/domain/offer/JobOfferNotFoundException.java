package pl.joble.domain.offer;

class JobOfferNotFoundException extends RuntimeException {
    public JobOfferNotFoundException(String message) {
        super(message);
    }
}
