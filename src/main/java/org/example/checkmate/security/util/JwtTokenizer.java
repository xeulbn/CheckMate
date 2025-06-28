package org.example.checkmate.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;


@Component
@Slf4j
public class JwtTokenizer {
    private final byte[] accessSecret;
    private final byte[] refreshSecret;

    public static Long ACCESS_TOKEN_EXPIRE_COUNT = 30 * 60 * 1000L; //30분
    public static Long REFRESH_TOKEN_EXPIRE_COUNT=7*24*60*60*1000L; //7일

    public JwtTokenizer(@Value("${spring.jwt.secret}")String accessSecret,@Value("${spring.jwt.secret}")String refreshSecret){
        this.accessSecret = accessSecret.getBytes(StandardCharsets.UTF_8);
        this.refreshSecret = refreshSecret.getBytes(StandardCharsets.UTF_8);
    }

    private String createToken(Long id, String email, String name, List<String> roles
            , Long expire, byte[] secretKey){

        Claims claims = Jwts.claims().setSubject(email);

        //필요한 정보들을 저장한다.
        claims.put("name",name);
        claims.put("userId",id);
        claims.put ("roles",roles);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+expire))
                .signWith(getSigningKey(secretKey))
                .compact();

    }

    public String createAccessToken(Long id, String email, String name, List<String> roles){
        return createToken(id,email,name,roles,ACCESS_TOKEN_EXPIRE_COUNT,accessSecret);
    }

    public String createRefreshToken(Long id, String email, String name, List<String> roles){
        return createToken(id,email,name,roles,REFRESH_TOKEN_EXPIRE_COUNT,refreshSecret);
    }

    public static Key getSigningKey(byte[] secretKey){
        return Keys.hmacShaKeyFor(secretKey);
    }

    public Long getUserIdFromToken(String token){
        log.error(token);
        String[] tokenArr = token.split(" ");
        log.error(tokenArr.toString());
        token = tokenArr[0];
        Claims claims = parseToken(token,accessSecret);
        return Long.valueOf((Integer)claims.get("userId"));
    }

    public Claims parseToken(String token, byte[] secretKey){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(secretKey))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public Claims parseAccessToken(String accessToken) {
        return parseToken(accessToken, accessSecret);
    }

    public Claims parseRefreshToken(String refreshToken) {
        return parseToken(refreshToken, refreshSecret);
    }
}