package ch.bbw.ap.shop.usermanager.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

public class JwtFilter extends OncePerRequestFilter {

    private final String AUTH_HEADER = "Authorization";
    private final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = getTokenFromHeader(request);

        try {
            if (jwt != null && jwtUtils.validateToken(jwt)) {
                Authentication auth = authManager.authenticate(new JwtToken(jwt));

                if (auth.isAuthenticated()) {
                    logger.info("Authentication: ", auth);
                    logger.debug("isAuthenticated: " + auth.isAuthenticated());
                    logger.info("Authenticated as " + auth.getName());

                    Optional<GrantedAuthority> authority = (Optional<GrantedAuthority>) auth.getAuthorities().stream().findFirst();
                    if (authority.isPresent()) {
                        boolean isAnonymous = authority.get().getAuthority().equals("ROLE_ANONYMOUS");
                        auth.setAuthenticated(!isAnonymous);
                    }


                    SecurityContextHolder.getContext().setAuthentication(auth);


                } else {
                    logger.debug("Not authenticated");
                    response.sendError(401, "User doesn't exist anymore");
                    return;
                }

            } else {
                logger.debug("No valid JWT provided");
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.error("JWT validation failed: ", e);
        } catch (MalformedJwtException e) {
            logger.warn("Provided JWT is malformed");
            response.sendError(400, "Malformed JWT provided");
            return;
        } catch (ExpiredJwtException e) {
            logger.warn("Expired JWT");
            response.sendError(401, "JWT expired");
            return;
        }

        filterChain.doFilter(request, response);

    }

    private String getTokenFromHeader(HttpServletRequest request) {
        logger.debug("Getting Authorization Header");
        String token = request.getHeader(AUTH_HEADER);

        if (token != null) {
            String result = token.replace("Bearer ", "").trim();

            logger.debug("Authorization Header: " + result);
            return result;
        }
        return null;
    }
}
