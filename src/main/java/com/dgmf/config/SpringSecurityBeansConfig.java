package com.dgmf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityBeansConfig {
    // Secure All Application Urls
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        authorize -> authorize
                                .anyRequest()
                                .authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

    // Define a Password Encoder
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Create In Memory Users
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails FranckDodo91 = User.builder()
                .username("FranckDodo91")
                .password(passwordEncoder().encode("*ruTuch7D7ub"))
                .roles("USER")
                .build();

        UserDetails Admin = User.builder()
                .username("Admin")
                .password(passwordEncoder().encode("*ruTuch7D7ub"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(FranckDodo91, Admin);
    }

}
