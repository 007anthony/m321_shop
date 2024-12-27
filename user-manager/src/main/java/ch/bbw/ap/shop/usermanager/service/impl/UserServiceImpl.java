package ch.bbw.ap.shop.usermanager.service.impl;

import ch.bbw.ap.shop.usermanager.model.User;
import ch.bbw.ap.shop.usermanager.repository.UserRepository;
import ch.bbw.ap.shop.usermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {

        if(userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            return null;
        }
            userRepository.save(user);

        return user;
    }
}
