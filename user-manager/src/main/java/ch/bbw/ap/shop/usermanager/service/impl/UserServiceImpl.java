package ch.bbw.ap.shop.usermanager.service.impl;

import ch.bbw.ap.shop.usermanager.model.Role;
import ch.bbw.ap.shop.usermanager.model.User;
import ch.bbw.ap.shop.usermanager.model.request.UserReset;
import ch.bbw.ap.shop.usermanager.repository.UserRepository;
import ch.bbw.ap.shop.usermanager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final CircuitBreaker circuitBreaker;

    public UserServiceImpl(UserRepository userRepository, CircuitBreakerFactory cbFactory) {
        this.userRepository = userRepository;
        this.circuitBreaker = cbFactory.create("userServiceCB");
    }

    @Override
    public User createUser(User user) {

        Optional<User> userOptional = this.getByUsername(user.getUsername());

        if(userOptional.isPresent()) {
            return null;
        }

        userRepository.save(user);

        return user;
    }

    @Override
    public Optional<User> getByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = circuitBreaker.run(
                () -> userRepository.findUserByUsername(username),
                throwable -> getFallback(throwable)
        );



        return user;


    }

    @Override
    public Optional<User> getById(Long id) {
        return circuitBreaker.run(
                () -> userRepository.findById(id),
                throwable -> getFallback(throwable)
        );
    }

    @Override
    public boolean delete(Long id) {
        Optional<User> user = this.getById(id);
        if(user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean resetPassword(Long id, UserReset reset) {
        Optional<User> userOptional = this.getById(id);

        if(userOptional.isPresent()) {
            User user = userOptional.get();

            if(BCrypt.checkpw(reset.getOldPassword(), user.getPassword())) {
                user.setPassword(reset.getNewPassword());
                userRepository.save(user);
                return true;
            }
        }

        return false;
    }

    private Optional<User> getFallback(Throwable e) {
        LOGGER.error("Database is unavailable: ", e);
        User user = new User();

        user.setRole(Role.ANONYMOUS);
        return Optional.of(user);
    }


}
