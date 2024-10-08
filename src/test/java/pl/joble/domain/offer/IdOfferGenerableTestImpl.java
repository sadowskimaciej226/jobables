package pl.joble.domain.offer;

class IdOfferGenerableTestImpl implements IdOfferGenerable {
    Integer id = 0;
    @Override
    public String generateId() {
        id+=1;
        return id.toString();
    }
}
