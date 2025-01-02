package ch.bbw.ap.shop.usermanager.controller;

import ch.bbw.ap.shop.usermanager.model.User;
import ch.bbw.ap.shop.usermanager.model.request.LoginUser;
import ch.bbw.ap.shop.usermanager.security.JwtUtils;
import ch.bbw.ap.shop.usermanager.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUser login) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {

        Optional<User> userOptional = userService.getByUsername(login.getUsername());

        if(userOptional.isEmpty()) {
            return ResponseEntity.status(401).build();
        }

        User user = userOptional.get();

        if(user.getPassword() == null || !BCrypt.checkpw(login.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(jwtUtils.generateToken(user.getUsername()));

    }

    @GetMapping("/me")
    public ResponseEntity<User> me() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Optional<User> user = userService.getById((Long) auth.getPrincipal());

        if(user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user.get());
    }
}
