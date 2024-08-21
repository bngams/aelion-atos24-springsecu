package fr.aelion.atos24.cyber.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.swing.*;

@FunctionalInterface
interface ActionListener {
    public void actionPerformed(String event);
}

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // TODO: enable and config CSRF
                .addFilterAfter((servletRequest, servletResponse, filterChain) -> {
                    // transform to http request object
                    HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
                    // get header Authorization content
                    String authorization = httpRequest.getHeader("Authorization");
                    // if token ok in Authorization
                    // TODO: create token validation logic
                    if(authorization != null && authorization.equals("MYTOKEN")) {
                        SecurityContextHolder.getContext().setAuthentication(
                                UsernamePasswordAuthenticationToken.authenticated("USERNAME", "MYTOKEN", null)
                        );
                    }
                    // do next filter
                    filterChain.doFilter(servletRequest, servletResponse);
                }, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(filter -> {
                    // /auth/signin /auth/signup, => authorize requests from /auth/*
                    filter.requestMatchers("auth/*").permitAll()
                            .requestMatchers("admin/*").hasRole("ADMIN")
                            .anyRequest().authenticated();
                }).build();
    }

    /*@Bean
    public UserDetailsService userDetailsService() {
        return null;
    }*/

}