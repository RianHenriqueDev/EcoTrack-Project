package br.com.rianhenrique.ecotrackproject.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTProvider {



    @Value("${secretToken.user}")
    private String secretToken;

    public DecodedJWT verifyToken(String token){
        token = token.replace("Bearer ", "");


        Algorithm algorithm = Algorithm.HMAC256(secretToken);

        var tokenDecoded = JWT.require(algorithm).build().verify(token);

        return tokenDecoded;
    }
}
