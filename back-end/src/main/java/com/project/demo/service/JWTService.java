package com.project.demo.service;

import com.project.demo.model.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    private String secretKey="";

    public JWTService() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        SecretKey sk= keyGen.generateKey();
        secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
    }

    public String generateToken(Users user) {
        Map<String, Object> claims = new HashMap<String, Object>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*60*10))
                .and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte[] encodedKey = Base64.getEncoder().encode(secretKey.getBytes());
        return Keys.hmacShaKeyFor(encodedKey);
    }

    public String extractUsername(String jwtToken) {
            return extractClaims(jwtToken,Claims::getSubject);
    }

    private <T> T extractClaims(String jwtToken, Function<Claims,T> claimResolver) {

        final Claims claims = extractAllClaims(jwtToken);
        return claimResolver.apply(claims);
    }




    private Claims extractAllClaims(String jwtToken) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    public boolean validateToken(String jwtToken, UserDetails userDetails) {
        final String username = extractUsername(jwtToken);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaims(jwtToken,Claims::getExpiration);
    }
}
