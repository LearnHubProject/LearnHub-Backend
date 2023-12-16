package org.learnhub.backend.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtils {

    private String secret;
    private Long expirationTime;


    private Algorithm algorithm;
    private JWTVerifier verifier;

    public JWTUtils( @Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") Long expirationTime){
        System.out.println(secret);
        this.secret = secret;
        this.expirationTime = expirationTime;
        algorithm = Algorithm.HMAC512(secret);
        verifier = JWT.require(algorithm).build();
    }

    public String generateToken(String username, String role){
        return JWT.create()
                .withClaim("username", username)
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(algorithm);
    }

    public DecodedJWT validateToken(String token){
        try {
            return verifier.verify(token);
        }
        catch (JWTVerificationException e){
            return null;
        }
    }
}
