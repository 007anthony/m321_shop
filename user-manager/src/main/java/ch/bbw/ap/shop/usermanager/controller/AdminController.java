package ch.bbw.ap.shop.usermanager.controller;

import ch.bbw.ap.shop.usermanager.model.User;
import ch.bbw.ap.shop.usermanager.model.request.PasswortReset;
import ch.bbw.ap.shop.usermanager.model.request.UserEdit;
import ch.bbw.ap.shop.usermanager.service.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        Optional<User> user = userService.getById(userId);

        if(user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok(user.get());
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<User> editUser(@PathVariable Long userId, @RequestBody UserEdit userEdit) {
        Optional<User> user =  userService.editUser(userId, userEdit);

        if(user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth.getPrincipal() == userId) {
            return ResponseEntity.badRequest().body("You tried to delete your self. This looks like an error. Please use /users/me to delete yourself.");
        }

        if(userService.deleteUser(userId)) {
            return ResponseEntity.ok("User deleted");
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/users/{userId}/reset")
    public ResponseEntity<String> resetPassword(@PathVariable Long userId, @Valid @RequestBody PasswortReset password) {
        if(userService.resetPassword(userId, password.getNewPassword())) {
            return ResponseEntity.ok("Password was changed successfully");
        }

        return ResponseEntity.notFound().build();
    }
}
