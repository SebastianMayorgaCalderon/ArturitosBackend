package com.brainstation.arturitos.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class JwtUtil {

    private String secret= "DanielaVH";

    public String generateToken(String subject, String email, String username) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(new Date(nowMillis+999999))
                .claim("email",email)
                .claim("username",username)
                .signWith(SignatureAlgorithm.HS256, secret);

        return builder.compact();
    }

    public HashMap<String, String> verifyToken(String token) throws MalformedJwtException {
        HashMap<String, String> userinfo = new HashMap<>();
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        userinfo.put("email",claims.get("email",String.class));
        userinfo.put("username",claims.get("username",String.class));
        return userinfo;
    }

}
