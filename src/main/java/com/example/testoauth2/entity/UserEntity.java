package com.example.testoauth2.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    private String username;
    private String password;
    private Set<GrantedAuthority> grantedAuthorities;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
