package pl.joble.domain.loginandregister;

import lombok.Builder;

@Builder
record Client(String id, String username, String aboutMe, Integer age, String location) {
}
