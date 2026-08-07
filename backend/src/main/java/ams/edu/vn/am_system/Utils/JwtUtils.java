package ams.edu.vn.am_system.Utils;

import java.util.Date;

import java.security.Key;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtils {
    @Value("${jwt.secret}")
    private String jwt_secret_signature;
    @Value("${jwt.accessTokenExpirationMs}")
    private int accessTokenExpirationMs;
    @Value("${jwt.refreshTokenExpirationMs}")
    private int refreshTokenExpirationMs;

    private Key key(String secret) {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateAccessToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setExpiration(new Date((new Date()).getTime() + accessTokenExpirationMs))
                .signWith(key(jwt_secret_signature))
                .compact();
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setExpiration(new Date(new Date().getTime() + refreshTokenExpirationMs))
                .signWith(key(jwt_secret_signature))
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key(jwt_secret_signature))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key(jwt_secret_signature))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
