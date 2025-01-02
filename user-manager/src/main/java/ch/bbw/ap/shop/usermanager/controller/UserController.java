package ch.bbw.ap.shop.usermanager.controller;

import ch.bbw.ap.shop.usermanager.mapper.UserMapper;
import ch.bbw.ap.shop.usermanager.model.User;
import ch.bbw.ap.shop.usermanager.model.request.UserCreate;
import ch.bbw.ap.shop.usermanager.service.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping
    public ResponseEntity<User> signUp(@Valid @RequestBody UserCreate request) {
        User response = userService.createUser(userMapper.map(request));

        if (response == null) {
            return ResponseEntity.status(409).build();
        }

        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/me")
    public ResponseEntity deleteMe()  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();



        boolean response = userService.delete((Long) authentication.getPrincipal());

        return response? ResponseEntity.ok().build(): ResponseEntity.notFound().build();

    }

}
