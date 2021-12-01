package com.example.testoauth2.service;

import com.example.testoauth2.entity.CustomUser;
import com.example.testoauth2.entity.UserEntity;
import com.example.testoauth2.repository.OAuthDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomDetailsService implements UserDetailsService {

    @Autowired
    OAuthDao oAuthDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = null;
        try {
            userEntity = oAuthDao.getUserDetails(username);
            CustomUser customUser = new CustomUser(userEntity);
            return customUser;
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
    }
}
