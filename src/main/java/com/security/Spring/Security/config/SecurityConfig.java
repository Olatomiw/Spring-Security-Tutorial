package com.security.Spring.Security.config;

import com.security.Spring.Security.repository.JpaUserDetailsService;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {

        http.csrf((csrf)->csrf.disable());
//        Authentication
        http
                .formLogin((formlogin)->formlogin
                        .defaultSuccessUrl("/api/v1/currentUser"));
        http.httpBasic(Customizer.withDefaults());
//        Authorization
        http.authorizeRequests(authorize -> authorize
                        .requestMatchers("/api/v1/welcome",
                                "/api/v1/create",
                                "api/v1/get/**"). permitAll()
                        .requestMatchers("api/v1/currentUser").hasAnyAuthority(UserRole.ROLE_ADMIN.toString(),UserRole.ROLE_NURSE.toString())
                        .anyRequest().authenticated());
        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return new JpaUserDetailsService();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
}
