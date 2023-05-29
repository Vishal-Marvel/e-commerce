package com.KiyoInteriors.ECommerce.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTTokenProvider {
     @Value("${KEY}")
     private String jwtSecret;

     @Value("${TIME}")
     private long expiration;

     public String generateToken(Authentication authentication) {
          String usernameOrEmail = authentication.getName();
          Date currentDate = new Date();
          long jwtExpirationDate = expiration;
          Date expiryDate = new Date(currentDate.getTime() + jwtExpirationDate);

          return Jwts.builder()
                  .setSubject(usernameOrEmail)
                  .setIssuedAt(currentDate)
                  .setExpiration(expiryDate)
                  .signWith(key())
                  .compact();
     }

     private Key key() {
          return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
     }

     //Get Username from JWT
     public String getUsername(String token) {
          Claims claims = Jwts.parserBuilder()
                  .setSigningKey(key())
                  .build()
                  .parseClaimsJws(token)
                  .getBody();
          return claims.getSubject();
     }

     //validate JWT
     public boolean validateToken(String token){
//          try {
               Jwts.parserBuilder()
                       .setSigningKey(key())
                       .build()
                       .parse(token);
               return true;
//          }
//          catch (MalformedJwtException e){
//               throw new APIException("Invalid Javascript Web Token", HttpStatus.UNAUTHORIZED);
//          }
//          catch (ExpiredJwtException e){
//               throw new APIException("Expired Javascript Web Token", HttpStatus.BAD_REQUEST);
//          }
//          catch (UnsupportedJwtException e){
//               throw new APIException("Unsupported Javascript Web Token", HttpStatus.BAD_REQUEST);
//          }
//          catch (IllegalArgumentException e){
//               throw new APIException("Javascript Web Token claims are empty", HttpStatus.BAD_REQUEST);
//          }
//          catch (SignatureException e){
//               throw new APIException("Invalid JWT", HttpStatus.BAD_REQUEST);
//          }
     }

}