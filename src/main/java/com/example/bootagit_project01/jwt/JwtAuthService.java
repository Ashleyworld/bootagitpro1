package com.example.bootagit_project01.jwt;


import com.example.bootagit_project01.user.user.entity.User;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.naming.AuthenticationException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class JwtAuthService {

    @Autowired
    private JwtUtil jwtUtil;
    private Map<String, String> refreshTokenMap = new HashMap<>();
    private Map<String, String> usernameTokenMap = new HashMap<>();

    public void addRefreshToken(String refreshToken, String username) {
        refreshTokenMap.put(refreshToken, username);
        usernameTokenMap.put(username, refreshToken);
    }

    public String refresh(String refreshToken) throws AuthenticationException {

        if (!jwtUtil.validateToken(refreshToken) || Objects.isNull(refreshTokenMap.get(refreshToken))) {
            throw new AuthenticationException("invalid refresh-token!") {
            };
        }

        Claims claims = jwtUtil.parseClaims(refreshToken);

        //accessToken 생성
        String accessToken = jwtUtil.createAccessToken(User.builder()
                .username(claims.get("username", String.class))
                .build());

        return accessToken;
    }

    public void logout(String username) {
        String refreshToken = usernameTokenMap.get(username);
        if (refreshToken != null) {
            refreshTokenMap.remove(refreshToken); // refreshTokenMap에서 제거
            usernameTokenMap.remove(username); // usernameTokenMap에서 제거
        }
    }
}
