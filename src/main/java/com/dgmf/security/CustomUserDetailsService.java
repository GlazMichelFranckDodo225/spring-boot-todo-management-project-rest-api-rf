package com.dgmf.security;

import com.dgmf.entity.User;
import com.dgmf.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        // Logic to load our own User Entity from the DB
        User userFromDb = userRepository.findByUsernameOrEmail(
                usernameOrEmail, usernameOrEmail
        ).orElseThrow(
                () -> new UsernameNotFoundException(
                        "User Not Exists By Username or Email"
                    )
        );

        // Convert User Set of Roles into User Set of Granted Authorities
        // because of Spring Security which expect These
        Set<GrantedAuthority> authorities = userFromDb.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        // Return a Spring Security Checked User
        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail,
                userFromDb.getPassword(),
                authorities
        );
    }
}
