package pl.joble.domain.loginandregister;

class IDClientGenerableTestImpl implements IdClientGenerable {
    Integer id = 0;
    @Override
    public String generateId() {
        id+=1;
        return id.toString();
    }
}
