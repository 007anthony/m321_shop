package ch.bbw.ap.shop.usermanager.controller;

import ch.bbw.ap.shop.usermanager.mapper.UserMapper;
import ch.bbw.ap.shop.usermanager.model.User;
import ch.bbw.ap.shop.usermanager.model.request.UserCreate;
import ch.bbw.ap.shop.usermanager.model.request.UserEdit;
import ch.bbw.ap.shop.usermanager.service.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> signUp(@Valid @RequestBody UserCreate request) {
        User response = userService.createUser(request);

        if (response == null) {
            return ResponseEntity.status(409).build();
        }

        return ResponseEntity.ok(response);

    }

    @PutMapping("/me")
    public ResponseEntity<User> editMe(@Valid @RequestBody UserEdit userEdit) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Optional<User> user = userService.editUser((Long) auth.getPrincipal(), userEdit);

        if(user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user.get());
    }

    @DeleteMapping("/me")
    public ResponseEntity deleteMe()  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();



        boolean response = userService.deleteUser((Long) authentication.getPrincipal());

        return response? ResponseEntity.ok().build(): ResponseEntity.notFound().build();

    }

}
