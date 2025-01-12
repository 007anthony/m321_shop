package ch.bbw.ap.shop.m321shopcore.security.jwt;

import ch.bbw.ap.shop.m321shopcore.security.Role;
import io.jsonwebtoken.Claims;
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
import java.util.Optional;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getCredentials();

        try {
            LOGGER.trace("Retrieved Username from Token");
            Claims claims = jwtUtils.getClaims(token);
            String username = claims.getSubject();
            String role = "ROLE_" + (String) claims.getOrDefault("role", Role.ANONYMOUS.toString());
            LOGGER.debug(username);


            return new JwtToken(
                    Long.parseLong(claims.getId()),
                    claims.getSubject(),
                    token,
                    List.of(new SimpleGrantedAuthority(role))
            );

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
