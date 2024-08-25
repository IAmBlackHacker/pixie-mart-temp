package com.pixie.mart.pixiemart.repositories.user;

import com.pixie.mart.pixiemart.models.collections.Address;
import com.pixie.mart.pixiemart.models.collections.user.User;
import com.pixie.mart.pixiemart.models.requests.UserAuthRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Slf4j
@Component
public class UserRepositoryManager {

    @Autowired
    private UserRepository userRepository;

    public Mono<User> save(User user) {
        return userRepository.save(user);
    }

    public Mono<Address> save(UserAuthRequest userAuthRequest, Mono<Address> address) {
        return address.flatMap(userAddress -> getUserById(userAuthRequest.getId()).flatMap(user -> {
            if (user.getAddress() == null) {
                user.setAddress(new ArrayList<>());
            }

            user.getAddress().add(userAddress.getId().toString());
            return userRepository.save(user).map(localUser -> userAddress);
        }));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).switchIfEmpty(Mono.empty()).block();
    }

    public Mono<User> getUserById(String id) {
        return userRepository.findById(id);
    }
}
