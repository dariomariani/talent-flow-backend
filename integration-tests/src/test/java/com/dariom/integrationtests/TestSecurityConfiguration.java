package com.dariom.integrationtests;

import com.dariom.filters.JwtSecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@TestConfiguration(proxyBeanMethods = false)
//@EnableWebSecurity
//@Configuration
//@Profile("test")
public class TestSecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final JwtSecurityFilter jwtSecurityFilter;

    public TestSecurityConfiguration(JwtSecurityFilter jwtSecurityFilter, AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
        this.jwtSecurityFilter = jwtSecurityFilter;
    }

    @Bean

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // Allow all requests
                .csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class);// Disable CSRF for
        // testing
        return http.build();
    }

}
