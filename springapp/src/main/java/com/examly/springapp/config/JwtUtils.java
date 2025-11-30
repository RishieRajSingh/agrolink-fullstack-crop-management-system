package com.examly.springapp.config;
 
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
 
@Component
public class JwtUtils {
    @Value("${spring.security.jwt.secret-key}")
    private String secretKey;
    private static final Date CURRENT_DATE = new Date();
    private static final Date EXPIRATION_DATE = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
 
    private static Logger myLogger = LoggerFactory.getLogger(JwtUtils.class);
 
    public String extractEmail(String token){
        myLogger.info("INSIDE extractUsername() METHOD IN JwtUtils "+token);
        return extractClaim(token, Claims::getSubject);
    }
 
    public Date extractExpiration(String token){
        myLogger.info("INSIDE extractExpiration() METHOD IN JwtUtils");
        return extractClaim(token, Claims::getExpiration);
    }
 
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        myLogger.info("INSIDE extractClaim() METHOD IN JwtUtils");
        final Claims claims= extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
 
    private Claims extractAllClaims(String token){
        myLogger.info("INSIDE extractAllClaim() METHOD IN JwtUtils "+token);
         try {
        return Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    } catch (ExpiredJwtException e) {
        myLogger.error("JWT Token has expired", e);
    } catch (IllegalArgumentException e) {
        myLogger.error("Unable to get JWT Token", e);
    } catch (Exception e) {
        myLogger.error("Error parsing JWT token", e);
    }
    return null;
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
 
    private boolean isTokenExpired(String token){
        myLogger.info("INSIDE isTokenExpired() METHOD IN JwtUtils");
        return extractExpiration(token).before(CURRENT_DATE);
    }
 
    public boolean validateToken(String token, UserDetails userDetails){
        myLogger.info("INSIDE validateToken() METHOD IN JwtUtils");
        final String username = extractEmail(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
 
    public String generateToken(String email,String role){
        myLogger.info("INSIDE generateToken() METHOD IN JwtUtils");
        Map<String, Object> claims = new HashMap<>();
        claims.put("role",role);
        return createToken(claims, email);
    }
 
    private String createToken(Map<String, Object> claims, String email){
        myLogger.info("INSIDE createToken() METHOD IN JwtUtils");
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(CURRENT_DATE)
                .setExpiration(EXPIRATION_DATE)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }  
}