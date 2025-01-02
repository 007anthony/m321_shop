package ch.bbw.ap.shop.usermanager.service.impl;

import ch.bbw.ap.shop.usermanager.model.User;
import ch.bbw.ap.shop.usermanager.repository.UserRepository;
import ch.bbw.ap.shop.usermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public User getByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);

        if(user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }


        return user.get();
    }
}
