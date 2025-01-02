package ch.bbw.ap.shop.usermanager.controller;

import ch.bbw.ap.shop.usermanager.mapper.UserMapper;
import ch.bbw.ap.shop.usermanager.model.User;
import ch.bbw.ap.shop.usermanager.model.request.UserCreate;
import ch.bbw.ap.shop.usermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping
    public ResponseEntity<User> signUp(@RequestBody UserCreate request) {
        User response = userService.createUser(userMapper.map(request));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(response == null) {
            return ResponseEntity.status(409).build();
        }

        return ResponseEntity.ok(response);

    }

}
