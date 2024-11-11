package pe.uni.buenaventurabackend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import pe.uni.buenaventurabackend.exception.AuthenticationException;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class CodeVerificationService {
    private static final String SECRET_KEY="OA3fd3UX+sHXUo/lsVCWolLHSEuoEb34Z6EkUoUVMQA=";


    public String createToken(String userId) {
        return getToken(userId);
    }
    private String getToken(String userId) {
        return Jwts
                .builder()
                .setSubject(userId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims getDataFromToken(String token) {
        if(!isTokenValid(token)){
            throw new AuthenticationException("El token ha expirado");
        }
        return getAllClaims(token);
    }

    public boolean isTokenValid(String token) {
        return token != null && !isTokenExpired(token);
    }

    private Claims getAllClaims(String token)
    {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }
}
