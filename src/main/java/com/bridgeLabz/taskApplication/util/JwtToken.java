package com.bridgeLabz.taskApplication.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtToken {
    private final String SECURITY = "security";

    public String createToken(int id) {
        String token = JWT.create().withClaim("id", id).sign(Algorithm.HMAC256(SECURITY));
        return token;
    }

    public int decode(String token) {
        int id = JWT.require(Algorithm.HMAC256(SECURITY)).build().verify(token).getClaim("id").asInt();
        return id;
    }
}
