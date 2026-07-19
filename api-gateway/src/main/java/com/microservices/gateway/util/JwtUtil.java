package com.microservices.gateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET =
            "mySecretKeymySecretKeymySecretKeymySecretKey";

    public String extractUsername(String token){

        return Jwts.parser()
                .verifyWith(
                        Keys.hmacShaKeyFor(
                                SECRET.getBytes())
                )
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token){

        try{
            extractUsername(token);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}