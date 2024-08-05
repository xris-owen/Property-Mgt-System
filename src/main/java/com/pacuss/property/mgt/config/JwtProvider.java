package com.pacuss.property.mgt.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtProvider {
    private final SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public String generateToken(Authentication auth){

        return Jwts.builder().setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 21600000)) // Expires after 6 hours
                .claim("username", auth.getName())
                .signWith(key)
                .compact();
    }

    public String getUsernameFromJwt(String jwToken){
        String jwt = jwToken.substring(7);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        return String.valueOf(claims.get("username"));
    }
}
