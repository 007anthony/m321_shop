package ch.bbw.ap.shop.usermanager.service;

import ch.bbw.ap.shop.usermanager.model.User;

import java.util.Optional;

public interface UserService {

    User createUser(User user);

    Optional<User> getByUsername(String username);

    Optional<User> getById(Long id);

    boolean delete(Long id);
}
