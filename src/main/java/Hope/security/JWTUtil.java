package Hope.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Map;

@Component
public class JWTUtil {

    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(String username, String role) throws ResponseStatusException, JWTCreationException {
        return JWT.create()
                .withSubject("User details")
                .withClaim("user", username)// Subject is the username
                .withClaim("role", role) // Claim is the role
                .withIssuedAt(new Date()) // Issued at current time
                .withIssuer("Hope") // Issuer is the application name
                .sign(Algorithm.HMAC256(secret)); // Sign the token with the secret
    }

    public Map<String, String> validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer("Hope")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return Map.of("user", jwt.getClaim("user").asString(), "role", jwt.getClaim("role").asString());
    }
}
