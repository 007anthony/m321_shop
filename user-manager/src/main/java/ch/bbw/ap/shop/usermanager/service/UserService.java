package ch.bbw.ap.shop.usermanager.service;

import ch.bbw.ap.shop.usermanager.model.User;
import ch.bbw.ap.shop.usermanager.model.request.UserCreate;
import ch.bbw.ap.shop.usermanager.model.request.UserEdit;
import ch.bbw.ap.shop.usermanager.model.request.UserReset;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(@Valid UserCreate user);

    List<User> getAll();

    Optional<User> getByUsername(String username);

    Optional<User> getById(Long id);

    Optional<User> editUser(Long id, UserEdit userEdit);

    boolean deleteUser(Long id);

    boolean resetPassword(Long id, UserReset reset);
}
