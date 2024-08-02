package com.example.bootagit_project01.config.security;

import com.example.bootagit_project01.user.user.domain.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private String secretKey = "test";

    // 토큰 유효시간 30분
    private long tokenValideTime = 30 * 60 * 1000L;

    private final UserDetailsService userDetailsService;

    // 객체 초기화, sercretKey를 Base64로 인코딩
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());

    }

    // JWT 토큰 생성
    public String createToken(String userPK, Role roles){
        Claims claims = Jwts.claims().setSubject(userPK);
        // JWT payload에 저장되는 정보

        claims.put("roles", roles); //정보 저장 (key-value)
        Date now = new Date();
            return Jwts.builder()
                    .setClaims(claims) // myClaims
                    .setIssuedAt(now) // 이 Claims이 언제 생겼는지 생성시기, 만료시기 계산할때 도움
                    .setExpiration(new Date(now.getTime() + tokenValideTime))
                    // set Expire Time
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    // 사용할 암호화 알고리즘과 signature에 들어갈 secret 값 세팅
                    .compact();
                    // 토큰을 생성하고 반환하는 메소드
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPK(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserPK(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN":"TOKEN 값"
    public String resolveToken(HttpServletRequest request){
        return request.getHeader("X-AUTH-TOKEN");
    }

    // 토큰의 유효성 + 만료일자 확인
    public  boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e){
            return false;
        }
    }


}