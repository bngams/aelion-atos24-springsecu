package fr.aelion.atos24.cyber.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import fr.aelion.atos24.cyber.models.entities.User;

import java.time.Instant;


public class TokenUtils {

    public static String generateJWT(User u) {
        return JWT.create()
                .withClaim("email", u.getEmail())
                .withClaim("datetime-claim", Instant.now())
                .sign(Algorithm.HMAC512("SECRET")); // TODO: store and encrypt in properties
    }

    public static DecodedJWT decodeJWT(String token) {
        // TODO: handle expiration, renew, verification exception
        return JWT.require(Algorithm.HMAC512("SECRET")).build().verify(token);
    }
}
