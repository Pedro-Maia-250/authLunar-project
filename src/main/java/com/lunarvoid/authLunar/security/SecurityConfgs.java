package com.lunarvoid.authLunar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfgs {

    @Autowired
    private SecurityFilterOnce filter;
    
    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity security) throws RuntimeException{
        return security.csrf((x) -> x.disable())
        .sessionManagement((s) -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests((a) -> a.requestMatchers(HttpMethod.GET, "/v3/api-docs").permitAll())
        .authorizeHttpRequests((a) -> a.requestMatchers(HttpMethod.POST, "/auth", "/users").permitAll())
        .authorizeHttpRequests((a) -> a.requestMatchers(HttpMethod.POST, "/jogos", "/users/{rule}").hasRole("ALTO"))
        .authorizeHttpRequests((a) -> a.requestMatchers(HttpMethod.DELETE, "/jogos", "/users/BAN").hasRole("ALTO"))
        .authorizeHttpRequests((a) -> a.requestMatchers(HttpMethod.PUT, "/jogos/{id}").hasRole("MEDIO"))
        .authorizeHttpRequests((a) -> a.requestMatchers(HttpMethod.GET, "/users", "/users/{id}").hasRole("MEDIO"))
        .authorizeHttpRequests((a) -> a.anyRequest().authenticated())
        .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
        return configuration.getAuthenticationManager();
    }

}

