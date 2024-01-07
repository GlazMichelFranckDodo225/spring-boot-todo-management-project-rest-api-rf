package com.dgmf.repository;

import com.dgmf.dto.user.UserDtoResponse;
import com.dgmf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<UserDtoResponse> findByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<UserDtoResponse> findByUsernameOrEmail(String username, String email);
}
