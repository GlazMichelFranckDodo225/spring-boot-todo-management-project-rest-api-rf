package com.dgmf.dto.user;

import com.dgmf.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserDtoRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Set<Role> roles;
}
