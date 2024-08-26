package com.example.projecttrendshopapp.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
//@RequiredArgsConstructor
public class JwtTokenUtil {

    private final String SECRET_KEY = "secret";

//    @Value("${secret.key}")
//    private String SECRET_KEY;



    // Bu metod, token'dan kullanıcı adını çıkarmak için kullanılır
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Bu metod, token'dan son kullanma tarihini çıkarmak için kullanılır
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Bu metod, token'dan belirli bir talebi (claim) çıkarmak için kullanılır
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Bu metod, token'ın içindeki tüm talepleri çıkarmak için kullanılır
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // Bu metod, token'ın süresinin dolup dolmadığını kontrol eder
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Bu metod, kullanıcı detaylarına dayalı olarak bir token oluşturur
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userName", userDetails.getUsername());
        return createToken(claims, userDetails.getUsername());
    }

    // Bu metod, verilen taleplere ve konuya dayalı olarak bir token oluşturur
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token geçerliliği 10 saat
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Bu metod, token'ın geçerli olup olmadığını kontrol eder
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
