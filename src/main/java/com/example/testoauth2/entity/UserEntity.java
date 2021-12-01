package com.example.testoauth2.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    private String username;
    private String password;
    private Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
}
