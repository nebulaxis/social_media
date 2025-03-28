package com.social.media.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class AppConfig {




    


    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }



    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{


        http.sessionManagement(management -> management
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
        authorizeHttpRequests(Authorize -> Authorize.requestMatchers("/api/**")
        .authenticated().anyRequest().permitAll())
        
         .addFilterBefore((Filter) new jwtValidator(), BasicAuthenticationFilter.class)
        .csrf(csrf -> csrf.disable())
        .cors(cors-> cors.configurationSource(corsConfigurationSource()));
        return http.build();
    }


    private CorsConfigurationSource corsConfigurationSource() {
       

       return new CorsConfigurationSource() {

        @Override
        @Nullable
        public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
          CorsConfiguration cfg=new CorsConfiguration();

          cfg.setAllowedOrigins(Arrays.asList(
            "http://localhost:4371/"
            ));

            cfg.setAllowedMethods((Collections.singletonList("*")));
         
            cfg.setAllowCredentials(true);
            cfg.setAllowedHeaders(Collections.singletonList("*"));
            cfg.setExposedHeaders(Arrays.asList(
                "Authorization"
                ));

                cfg.setMaxAge(3600L);
            return cfg;
        }
        
       };
    }



    @Bean
     PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}