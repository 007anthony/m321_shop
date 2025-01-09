package ch.bbw.ap.shop.productmanager.security;

import ch.bbw.ap.shop.productmanager.client.response.UserResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;

public class UserToken implements Authentication {

    private String token;
    private UserResponse userResponse;

    private boolean isAuthenticated;

    public UserToken(String token) {
        this.token = token;
        this.userResponse = null;
        this.isAuthenticated = false;
    }

    public UserToken(String token, UserResponse userResponse) {
        this.token = token;
        this.userResponse = userResponse;
        this.isAuthenticated = true;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority("ROLE_" + userResponse.getRole()));
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        return userResponse;
    }

    @Override
    public Object getPrincipal() {
        return userResponse.getId();
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return userResponse.getUsername();
    }
}
