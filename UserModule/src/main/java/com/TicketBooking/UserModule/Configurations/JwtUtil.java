package com.TicketBooking.UserModule.Configurations;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private String secretKey="5947b9477a3f874a0b36ec2d0d8eb08961958b48618ada5f27d4211b848e136eb04c2ea5128ec5a5bf64aaa8dffec994d8a24f6fde57234cdb030b957ca919cd";

    /*This method will basically convert our secret key to byte array format and return cryptographic key object  */
    private Key getSigningKey() {
    byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email, Collection<? extends GrantedAuthority> authorities){
    List<String> role = authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList();

    return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSigningKey())
                .compact();
    }

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
    }

    public String extractEmail(String token){
        return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
    }

    public Date extractExpiration(String token){
        return extractClaims(token).getExpiration();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean isvalidToken(String token, UserDetails userDetails){

        String email= extractEmail(token);
        return email.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }


}
