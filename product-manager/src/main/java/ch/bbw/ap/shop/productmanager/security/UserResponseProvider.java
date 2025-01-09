package ch.bbw.ap.shop.productmanager.security;

import ch.bbw.ap.shop.productmanager.client.UserClient;
import ch.bbw.ap.shop.productmanager.client.response.UserResponse;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UserResponseProvider implements AuthenticationProvider {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserClient userClient;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String jwt = (String) authentication.getCredentials();

        LOGGER.debug("Get User from User Manager");
        UserResponse user = userClient.getMe("Bearer " + jwt);

    LOGGER.info("Authenticating as %s", user.getUsername());

        return new UserToken(jwt, user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UserToken.class.isAssignableFrom(authentication);
    }
}
