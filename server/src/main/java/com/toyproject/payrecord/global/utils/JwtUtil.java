package com.toyproject.payrecord.global.utils;

import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.security.Principal;
import java.util.Date;
import java.util.function.Function;


public class JwtUtil {

    @Value("${jwt.issuer}")
    private static String ISSUER;

    @Value("${jwt.subject}")
    private static String SUBJECT;

    @Value("${jwt.refresh_secret}")
    private static String REFRESH_SECRET;

    private static final Long EXPIRE_TIME = 60L * 60 * 2 * 1000;
    private static final Long REPRESH_EXPIRE_TIME = 60L * 60 * 24 * 30 * 1000;

    private static Key key;

    public JwtUtil(String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public static String createToken(Long empId, String email) {
        Date now = new Date();

        String token = Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(SUBJECT)
                .claim("empId", empId)
                .claim("email", email)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

    public static String refreshToken(Long empId, String email) {
        Date now = new Date();

        String token = Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(SUBJECT)
                .claim("empId", empId)
                .claim("email", email)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REPRESH_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

    public Claims getClaims(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public String extractUserEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token) {
        return (!isTokenExpired(token));
    }
}
