package com.sr.L.DShop.service.concrete;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    String keySecret;

    JwtService(){
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGenerator.generateKey();
            keySecret = Base64.getEncoder().encodeToString(secretKey.getEncoded());


        }
        catch (RuntimeException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public SecretKey getKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(keySecret));
    }

    public String generateToken(String username){

        Map<String,String> claims = new HashMap<>();


        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(getKey())
                .compact()
                ;
    }

    public String getUsername(String token){
        return this.getClaims(new Function<Claims, String>() {
            @Override
            public String apply(Claims claims) {
                return claims.getSubject();
            }
        }, token);
        //return this.getClaims(Claims::getSubject,token);
        //return this.getAllClaims(token).getSubject();
    }

    public Boolean isTokenExpired(String token){
        return getClaims(Claims::getExpiration, token).before(new Date(System.currentTimeMillis()));
    }

    //Util.<String>show("Hello");
    public <T> T getClaims(Function<Claims,T> claimResolver, String token){
        return claimResolver.apply(getAllClaims(token));
    }

    public Claims getAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }
}
