package com.example.bootagit_project01.jwt;

//JWT 관련 메소드를 제공하는 클래스
// JWT 생성, 검증, 추출 등의 기능을 수행한다.

import com.example.bootagit_project01.config.security.MyUserDetail;
import com.example.bootagit_project01.user.user.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.thymeleaf.util.StringUtils;

import java.security.Key;
import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class JwtUtil {

    private Key key; //JWT 서명을 위한 시크릿 키
    private long accessTokenExpTime; //AccessToken 의 만료 시간
    private long refreshTokenExpTime;

    public JwtUtil(

            //secretKey 와 accessTokenExpTime을 받아옴
            String secretKey, long acessTokenExpTime, long refreshTokenExpTime
    ){
        // 받아온 객체로 키를 설정(base64 로 기본키로 설정)
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        // 만료 시간 초기화.
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpTime = acessTokenExpTime;
        this.refreshTokenExpTime = refreshTokenExpTime;
    }



//    // 토큰에서 클레임(Claims) 추출
//    public Claims parseClaims(String token) {
//        try {
//            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//        } catch (ExpiredJwtException e) {
//            return e.getClaims();
//        }
//    }


    //UserEntity를 바탕으로 accessToken을 생성함.
    public String createAccessToken(User user) {return createToken(user, accessTokenExpTime); }

    public String createRefreshToken(User user) {return createToken(user, refreshTokenExpTime); }


    // JWT 생성
    private String createToken(User user, long expireTime){

        // JWT Claims에 사용자 정보와 권한 저장
        Claims claims = (Claims) Jwts.claims();
        claims.put("userid", user.getUserid());
        claims.put("username", user.getUsername());
        claims.put("email", user.getEmail());
        claims.put("role", "ROLE_USER"); // TODO

        // 만료 시간
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(expireTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰에서 사용자 아이디 추출
    public Long getUserId(String token) {
        return parseClaims(token).get("userId", Long.class);
    }

    // 토큰을 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);

            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

        // JWT 에서 Claims를 추출
        public Claims parseClaims(String accessToken){
            try {
                return Jwts
                        .parserBuilder()
                        .setSigningKey(key).build().parseClaimsJws(accessToken).getBody();

                // setSigningKey : 생성하거나 디코딩하기 위해 만드려고 할 때 서명키 필요
                // key : JWT에 디지털 서명하는데 사용되는 비밀키
                // parseClaimsJws : 클레임 jws를 전달함

            } catch (ExpiredJwtException e) {
                return e.getClaims();
            }
        }

        // Authorization 헤더에서 토큰 추출
        public String extractTokenFromHeader(String authorization){
            if (StringUtils.isEmpty(authorization) || !authorization.startsWith("Bearer")) {
                return null;
            }
            return authorization.replaceAll("Bearer", "").trim();
        }

        // Access Token에 들어있는 정보를 꺼내 Authentication 객체를 생성 후 반환한다.
        public UsernamePasswordAuthenticationToken getAuthentication (String accessToken){
            // 토큰의 Payload에 저장된 Claim들을 추출한다.
            Claims claims = parseClaims(accessToken);

            if (claims.get("role") == null) {
                // 권한 정보 없는 토큰

                throw new AuthenticationServiceException("no exist role!!");
            }

            // Claim 에서 권한 정보를 추출한다.
            Collection<? extends GrantedAuthority> autorities = Arrays
                    .stream(claims.get("role").toString().split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            // Claim에 저장된 사용자 아이디를 통해 UserDetails 객체를 생성한다.
            UserDetails principal = MyUserDetail.create((String) claims.get("memberId"), null, autorities);

            // Authentication 객체를 생성하여 반환한다.
            return new UsernamePasswordAuthenticationToken(principal, "", autorities);


        }


    }

