package com.govnorganization.personalblog.personalblog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/home", "/article/**", "/css/**", "/new.css").permitAll()
            .requestMatchers(
                "/admin/**",
                "/new_article/**",
                "/add_article/**",
                "/delete/**",
                "/article_update/**")
            .authenticated()
            .anyRequest()
            .permitAll())
        .httpBasic(Customizer.withDefaults());

    return http.build();
  }
}

