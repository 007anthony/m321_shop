package ch.bbw.ap.shop.productmanager.security;

import ch.bbw.ap.shop.productmanager.client.UserClient;
import ch.bbw.ap.shop.productmanager.client.response.UserResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class UserManagerAuthFilter extends OncePerRequestFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserClient userClient;

    @Autowired
    private AuthenticationManager authManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        LOGGER.debug("Retrieve Authorization Token");
        String header = request.getHeader("Authorization");

        if(header == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = header.replace("Bearer ", "").trim();

        Authentication auth = authManager.authenticate(new UserToken(jwt));

        if(auth == null) {
            LOGGER.debug("User not found");

            response.sendError(401, "Unauthorized");
            return;
        }


        SecurityContextHolder.getContext().setAuthentication(auth);
        LOGGER.info("User is authenticated");

        filterChain.doFilter(request, response);
    }
}
