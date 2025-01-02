package ch.bbw.ap.shop.usermanager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Bean
    public AuthenticationManager authManager() {
        return new ProviderManager(List.of(jwtAuthenticationProvider));
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorization ->
                        authorization
                                .requestMatchers(HttpMethod.POST, "/users", "/auth/login").permitAll()
                                .requestMatchers("/error", "/roles").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}
