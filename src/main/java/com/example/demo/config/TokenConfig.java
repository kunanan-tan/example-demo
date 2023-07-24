package com.example.demo.config;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.bean.UserBean;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;
import com.auth0.jwt.JWT;

@Service
@RequiredArgsConstructor
public class TokenConfig {

    @Value("${jwt.token.prefix}")
    String tokenPrefix;
    @Value("${app.token.secret}")
    private String secret;
    @Value("${app.token.issuer}")
    private String issuer;

    public String generateToken(UserBean user) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 60);
        Date expiresAt = calendar.getTime();

        return JWT.create()
                .withIssuer(issuer)
                .withClaim("principal", user.getEmail())
                .withClaim("role", "USER")
                .withExpiresAt(expiresAt)
                .sign(algorithm());

    }

    public DecodedJWT verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm())
                    .withIssuer(issuer)
                    .build();

            return verifier.verify(token);

        } catch (Exception e) {
            return null;
        }
    }

    private Algorithm algorithm() {
        return Algorithm.HMAC256(secret);
    }


}
