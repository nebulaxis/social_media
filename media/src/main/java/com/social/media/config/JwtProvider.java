
package com.social.media.config;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtProvider {

    private static final String SECRET = "this_is_a_secure_secret_key_with_32_bytes";
    private static final SecretKey key;

    static {
        try {
            key = Keys.hmacShaKeyFor(SECRET.getBytes()); // Ensure 32-byte secret
        } catch (Exception e) {
            throw new RuntimeException("Error initializing JWT key", e);
        }
    }

    public static String generateToken(Authentication auth) {
        return Jwts.builder()
            .setIssuer("socialmedia")
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1-day expiration
            .claim("email", auth.getName())
            .signWith(key)
            .compact();
    }

    public static String getEmailFromJwtToken(String jwt) {
        try {
            jwt = jwt.substring(7); // Remove "Bearer " prefix
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
            return claims.get("email", String.class);
        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }
}






/*package com.social.media.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {

    private static SecretKey key= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
        
        public static String generateToken(Authentication auth){
            
            String jwt= Jwts.builder()
            .setIssuer("socialmedia").setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime()+86400000))
            .claim("email", auth.getName())
            .signWith(key)
        .compact();


        return jwt;
    }

    public static String getEmailFromJwtToken(String jwt){
        //Bearer 


        jwt=jwt.substring(0, 7);
        Claims claims=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        String email= String.valueOf(claims.get("email"));

        return email;
    }
}*/
