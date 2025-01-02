package ch.bbw.ap.shop.usermanager.service.impl;

import ch.bbw.ap.shop.usermanager.model.Role;
import ch.bbw.ap.shop.usermanager.model.User;
import ch.bbw.ap.shop.usermanager.repository.UserRepository;
import ch.bbw.ap.shop.usermanager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

        if(this.getByUsername(user.getUsername()) != null) {
            return null;
        }
            userRepository.save(user);

        return user;
    }

    @Override
    public User getByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = circuitBreaker.run(
                () -> userRepository.findUserByUsername(username),
                throwable -> getByUsernameFallback(throwable)
        );

        if(user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }


        return user.get();


    }

    private Optional<User> getByUsernameFallback(Throwable e) {
        LOGGER.error("Database is unavailable: ", e);
        User user = new User();

        user.setRole(Role.ANONYMOUS);
        return Optional.of(user);
    }


}
