package pl.joble.domain.offer;

import java.util.UUID;

class IdOfferGenerator implements IdOfferGenerable{

    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
