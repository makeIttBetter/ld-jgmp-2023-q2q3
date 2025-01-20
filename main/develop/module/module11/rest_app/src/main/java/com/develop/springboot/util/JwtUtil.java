package com.develop.springboot.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * JwtUtil class is a utility class for generating and validating JWT tokens.
 */
@Component
public class JwtUtil {

    private final Key key;
    @Value("${jwt.expiration}")
    private long expirationTime;

    // Assuming we want to generate the key rather than using a property
    public JwtUtil() {
        // Generate a key for the HS512 algorithm
        key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    /**
     * Generates a JWT token for the given username.
     * <p> The token is valid for the amount of time specified in the expirationTime property.
     * <p> The token is signed using the key generated in the constructor.
     * The key is generated using the HS512 algorithm.
     *
     * @param username the username to generate the token for
     * @return the generated JWT token
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }

    /**
     * Extracts the username from the given JWT token.
     *
     * @param token the JWT token to extract the username from
     * @return the username extracted from the token
     */
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * Extracts the expiration date from the given JWT token.
     *
     * @param token the JWT token to extract the expiration date from
     * @return the expiration date extracted from the token
     */
    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    /**
     * Checks if the given JWT token is expired.
     *
     * @param token the JWT token to check
     * @return true if the token is expired, false otherwise
     */
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Validates the given JWT token.
     * <p> The token is valid if it is not expired.
     *
     * @param token the JWT token to validate
     * @return true if the token is valid, false otherwise
     */
    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    /**
     * Extracts all claims from the given JWT token.
     * <p> The claims are extracted using the key generated in the constructor.
     * The key is generated using the HS512 algorithm.
     *
     * @param token the JWT token to extract the claims from
     * @return the claims extracted from the token
     * @see JwtUtil#JwtUtil() and
     * @see JwtUtil#generateToken(String)
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
