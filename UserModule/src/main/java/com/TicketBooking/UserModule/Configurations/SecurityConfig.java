package com.TicketBooking.UserModule.Configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.TicketBooking.UserModule.Service.CustomUserDetailsService;

@Configuration
@EnableMethodSecurity
public class SecurityConfig{

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtAuthenticationFilter JwtAuthFilter;

    SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtAuthenticationFilter JwtAuthFilter){
        this.customUserDetailsService=customUserDetailsService;
        this.JwtAuthFilter=JwtAuthFilter;
    }
    /* This class is used for security configurations */

    /* This method will take object of class HttpSecurity as input and return object of SecurityFilterChain */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/signup","/auth/login").permitAll()
                .anyRequest().authenticated()
            )
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(JwtAuthFilter,UsernamePasswordAuthenticationFilter.class);

            return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
