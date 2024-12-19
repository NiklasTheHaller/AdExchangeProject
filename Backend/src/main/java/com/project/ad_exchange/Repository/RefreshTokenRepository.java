package com.project.ad_exchange.Repository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RefreshTokenRepository {

    private final Map<String, UserDetails> tokens = new HashMap<>();

    public UserDetails findUserDetailsByToken(String token) {
        return tokens.get(token);
    }

    public void save(String token, UserDetails userDetails) {
        tokens.put(token, userDetails);
    }

    public void deleteByToken(String token) {
        tokens.remove(token);
    }
}
