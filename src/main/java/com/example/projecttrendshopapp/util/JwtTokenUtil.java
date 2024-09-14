package com.example.projecttrendshopapp.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final String SECRET_KEY = "secret";

//    @Value("${secret.key}")
//    private String SECRET_KEY;

    // Bu metod, tokenden userin adını cixarmaq ucun istifade olunur
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Bu metod, tokendan son istifade etmek tarixini cixarmaq ucun istifade olunur
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Bu metod, tokenden belirli (claim) cixarmaq ucun istifade olunur
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Bu metod, tokenin içindeki butun telebleri cixarmaq ucun istifade olunur
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // Bu metod, tokenin vaxtinin dolub dolmadiqini yoxlayir
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Bu metod, userin detaylarina dayali olaraq token generate edir
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userName", userDetails.getUsername());
        return createToken(claims, userDetails.getUsername());
    }

    // Bu metod, verilen teleblere uyqun olaraq bir token oluşturur
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token geçerliliği 10 saat
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Bu metod, tokenin geçerli olub olmadığını yoxlayir
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
