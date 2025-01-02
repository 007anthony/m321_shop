package ch.bbw.ap.shop.usermanager.service;

import ch.bbw.ap.shop.usermanager.model.User;
import org.springframework.stereotype.Service;

public interface UserService {

    User createUser(User user);

    User getByUsername(String username);
}
