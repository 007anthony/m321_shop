package ch.bbw.ap.shop.usermanager.security;

import ch.bbw.ap.shop.usermanager.model.User;
import ch.bbw.ap.shop.usermanager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getCredentials();

        try {
            LOGGER.trace("Retrieved Username from Token");
            String username = jwtUtils.getPrincipal(token);
            LOGGER.debug(username);

            LOGGER.debug("Retrieve User from database");
            User user = userService.getByUsername(username);
            return new JwtToken(user.getUsername(), token, List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
            LOGGER.error("JWT Validation failed: ", e);
        } catch (UsernameNotFoundException e) {
            LOGGER.error("User doesn't exist");
        }

        return new JwtToken((String) authentication.getCredentials());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtToken.class.isAssignableFrom(authentication);
    }
}
