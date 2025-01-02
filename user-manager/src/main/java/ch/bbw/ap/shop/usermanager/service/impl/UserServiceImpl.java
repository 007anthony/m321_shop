package ch.bbw.ap.shop.usermanager.service.impl;

import ch.bbw.ap.shop.usermanager.mapper.UserMapper;
import ch.bbw.ap.shop.usermanager.model.Role;
import ch.bbw.ap.shop.usermanager.model.User;
import ch.bbw.ap.shop.usermanager.model.request.UserCreate;
import ch.bbw.ap.shop.usermanager.model.request.UserEdit;
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

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final CircuitBreaker circuitBreaker;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, CircuitBreakerFactory cbFactory, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.circuitBreaker = cbFactory.create("userServiceCB");
        this.userMapper = userMapper;
    }

    @Override
    public User createUser(UserCreate userCreate) {

        Optional<User> userOptional = this.getByUsername(userCreate.getUsername());

        if(userOptional.isPresent()) {
            return null;
        }

        User user = this.userMapper.map(userCreate);

        userRepository.save(user);

        return user;
    }

    @Override
    public List<User> getAll() {
        Iterator<User> iterator = userRepository.findAll().iterator();

        List<User> users = new ArrayList();
        while(iterator.hasNext()) {
            User user = iterator.next();

            users.add(user);
        }


        return users;
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
    public Optional<User> editUser(Long id, UserEdit userEdit) {
        Optional<User> user = this.getById(id);

        if(user.isEmpty()) {
            return Optional.empty();
        }

        User mergedUser = this.userMapper.merge(userEdit, user.get());

        userRepository.save(mergedUser);

        return Optional.of(mergedUser);
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
