package com.rurallearning.platform.security;




import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

    @Service
    public class JwtService {

        @Value("${jwt.secret}")
        private String secret;

        private SecretKey getSigningKey() {

            byte[] keyBytes = Decoders.BASE64.decode(
                    java.util.Base64.getEncoder()
                            .encodeToString(secret.getBytes())
            );

            return Keys.hmacShaKeyFor(keyBytes);
        }

        public String generateToken(String email) {

            return Jwts.builder()
                    .subject(email)
                    .issuedAt(new Date())
                    .expiration(
                            new Date(System.currentTimeMillis() + 1000 * 60 * 60)
                    )
                    .signWith(getSigningKey())
                    .compact();
        }

        public String extractEmail(String token) {

            Claims claims = Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return claims.getSubject();
        }

        public boolean isTokenValid(String token, String email) {

            try {
                String tokenEmail = extractEmail(token);

                return tokenEmail.equals(email);

            } catch (Exception e) {

                return false;
            }
        }
    }

