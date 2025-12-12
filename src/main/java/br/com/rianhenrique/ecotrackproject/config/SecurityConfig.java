package br.com.rianhenrique.ecotrackproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {


    public String[] AUTHORIZE_SWAGGER = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources/**",
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(cs -> cs.disable()).authorizeHttpRequests(authorize -> {
            authorize.requestMatchers(AUTHORIZE_SWAGGER).permitAll()
                    .requestMatchers("/auth/login").permitAll();
            authorize.anyRequest().authenticated();
        });
       return http.build();
       
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
