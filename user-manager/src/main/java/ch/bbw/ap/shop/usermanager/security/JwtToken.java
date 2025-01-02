package ch.bbw.ap.shop.usermanager.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public class JwtToken implements Authentication {

    private final String username;
    private final String credentials;
    private final Collection<GrantedAuthority> authorities;
    private boolean isAuthenticated;

    public JwtToken(String credentials){
        this.username = null;
        this.credentials = credentials;
        this.authorities = null;
        this.isAuthenticated = false;
    }
    public JwtToken(String username, String credentials, Collection<GrantedAuthority> authorities) {
        this.username = username;
        this.credentials = credentials;
        this.authorities = authorities;
        this.isAuthenticated = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.username;
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
        return null;
    }
}
