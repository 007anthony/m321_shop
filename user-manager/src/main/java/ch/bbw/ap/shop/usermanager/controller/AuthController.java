package ch.bbw.ap.shop.usermanager.controller;

import ch.bbw.ap.shop.usermanager.model.User;
import ch.bbw.ap.shop.usermanager.model.request.LoginUser;
import ch.bbw.ap.shop.usermanager.security.JwtUtils;
import ch.bbw.ap.shop.usermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    // TODO: add circuitbreaker
    public ResponseEntity<String> login(@RequestBody LoginUser login) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        try {
            User user = userService.getByUsername(login.getUsername());
            if (!BCrypt.checkpw(login.getPassword(), user.getPassword())) {
                return ResponseEntity.status(401).build();
            }

            return ResponseEntity.ok(jwtUtils.generateToken(user.getUsername()));
        }
        catch(UsernameNotFoundException e) {
            return ResponseEntity.status(401).build();
        }

    }
}