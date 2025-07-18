package tj.alimov.productservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tj.alimov.productservice.exception.user.TokenNotProvidedException;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private final Key SECRET_KEY;
    public JwtService(@Value("${jwt.secret}") String value){
        SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(value));
    }

    public boolean validateToken(String token){
        return isTokenExpired(token);
    }
    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }
    public boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }
    public Claims extractClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Long getUserId(HttpServletRequest request){
        String token = retrieveToken(request);
        return extractUserId(token);
    }
    public Long extractUserId(String token){
        Claims claims = extractClaims(token);
        return claims.get("userId", Long.class);
    }

    private String retrieveToken(HttpServletRequest request){
        String header = request.getHeader("Authentication");
        if(header == null || !header.startsWith("Bearer")){
            throw new TokenNotProvidedException("Token is not provided");
        }
        String token = header.substring("Bearer ".length());
        return token;
    }
}
