package     com.bookstore.user_service.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "mysecretkeymysecretkeymysecretkey";

    private static final long EXPIRATION = 1000 * 60 * 60; // 1 hour

    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Generate Token
    public static String generateToken(String username, String role) {
        System.out.println("SECRET (User Service): " + SECRET);
        String token =  Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        System.out.println("Generated Token: " + token);

        return token;
    }

    // Validate Token
    public static String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}