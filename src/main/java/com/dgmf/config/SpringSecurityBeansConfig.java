package com.dgmf.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurityBeansConfig {
    // We do not have to manually provide an implementation of
    // UserDetailsService to Authentication Manager ==> Spring security 6
    // will automatically use this UserDetailsService and call its
    // "loadUserByUsername()" Method
    private final UserDetailsService userDetailsService;

    // Secure All Application Urls
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        authorize -> {
                            /*// Only ADMIN Role
                            authorize.requestMatchers(
                                    HttpMethod.POST,
                                    "/api/v1/**"
                            ).hasRole("ADMIN");
                            authorize.requestMatchers(
                                    HttpMethod.PUT,
                                    "/api/v1/**"
                            ).hasRole("ADMIN");
                            authorize.requestMatchers(
                                    HttpMethod.DELETE,
                                    "/api/v1/**"
                            ).hasRole("ADMIN");
                            // ADMIN and USER Roles
                            authorize.requestMatchers(
                                    HttpMethod.GET,
                                    "/api/v1/**"
                            ).hasAnyRole("ADMIN", "USER");
                            authorize.requestMatchers(
                                    HttpMethod.PATCH,
                                    "/api/v1/**"
                            ).hasAnyRole("ADMIN", "USER");*/
                            // To Provide Public Access to a Particular
                            // HTTP Method ==> Remember to adjust above
                            /*authorize.requestMatchers(
                                    HttpMethod.GET,
                                    "/api/v1/**"
                            ).permitAll();*/
                            // All other Requests must be Authenticated
                            authorize.anyRequest().authenticated();
                        }
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
    /*@Bean
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
    }*/

    // Define an Authentication Manager
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
