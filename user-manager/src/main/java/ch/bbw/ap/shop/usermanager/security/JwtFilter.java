package ch.bbw.ap.shop.usermanager.security;

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

public class JwtFilter extends OncePerRequestFilter {

    private final String AUTH_HEADER = "Authorization";
    private final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authManager;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt =  getTokenFromHeader(request);

        if(jwt != null) {
            Authentication auth = authManager.authenticate(new JwtToken(jwt));
            if(auth != null) {
                logger.debug("isAuthenticated: " + auth.isAuthenticated());
                logger.info("Authenticated as " + auth.getPrincipal());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            else {
                logger.debug("Not authenticated");
            }

        }
        else {
            logger.debug("No JWT provided");
        }

        filterChain.doFilter(request, response);

    }

    private String getTokenFromHeader(HttpServletRequest request) {
        logger.debug("Getting Authorization Header");
        String token = request.getHeader(AUTH_HEADER);

        if(token != null) {
            String result = token.replace("Bearer ", "").trim();

            logger.debug("Authorization Header: " + result);
            return result;
        }
        return null;
    }
}
