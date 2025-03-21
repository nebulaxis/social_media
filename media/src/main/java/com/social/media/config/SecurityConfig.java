/*package com.social.media.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/home", "/public/**" ,"/new").permitAll() // Allow these URLs without authentication
                .anyRequest().authenticated() // Secure all other endpoints
            )
            .formLogin(form -> form
                .loginPage("/login") // Custom login page (optional)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // Logout URL
                .logoutSuccessUrl("/login?logout") // Redirect after logout
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Password encoding using BCrypt
    }
}
*/