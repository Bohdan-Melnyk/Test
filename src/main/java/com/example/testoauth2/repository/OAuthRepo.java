package com.example.testoauth2.repository;

import com.example.testoauth2.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OAuthRepo {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OAuthRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserEntity getUserDetails(String username) {
        String sql = "SELECT USERNAME, PASSWORD FROM USERS WHERE USERNAME = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, (rs, rowNum) ->
            new UserEntity(
                    rs.getString("USERNAME"),
                    rs.getString("PASSWORD")
            )
        );
    }
}
