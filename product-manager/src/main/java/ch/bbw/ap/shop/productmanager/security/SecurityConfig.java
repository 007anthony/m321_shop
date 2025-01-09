package ch.bbw.ap.shop.productmanager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserResponseProvider userResponseProvider;

    @Bean
    AuthenticationManager authManager() {
        return new ProviderManager(List.of(userResponseProvider));
    }

    @Bean
    UserManagerAuthFilter authFilter() {
        return new UserManagerAuthFilter();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(registry ->
                        registry
                                .requestMatchers("/error").permitAll()
                                .requestMatchers(HttpMethod.GET).permitAll()
                                .anyRequest().hasRole("ADMIN")
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
