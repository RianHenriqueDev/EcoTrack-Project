package br.com.rianhenrique.ecotrackproject.useCases.user;

import br.com.rianhenrique.ecotrackproject.DTOs.UserAuthenticationDTO;
import br.com.rianhenrique.ecotrackproject.controllers.UserController;
import br.com.rianhenrique.ecotrackproject.entities.UserEntity;
import br.com.rianhenrique.ecotrackproject.repositories.UserRepository;
import br.com.rianhenrique.ecotrackproject.utils.errors.EntityException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthenticateUserUseCase {

    @Value("${secretToken.user}")
    private String secretToken;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserAuthenticationDTO authUser(UserController.authenticateUserUseCase authenticateUserUseCase) {

        var user = this.userRepository.findByEmailIgnoreCase(authenticateUserUseCase.email()).orElseThrow(()-> {
            throw new EntityException("E-mail ou Senha incorretos!", HttpStatus.UNAUTHORIZED);
        });

        System.out.println(user);

        var passwordHashed = this.passwordEncoder.matches(authenticateUserUseCase.password(),user.getPassword());

        if(!passwordHashed) {
            throw new EntityException("E-mail ou Senha incorretos!", HttpStatus.UNAUTHORIZED);
        }


        var tokenHashed = Algorithm.HMAC256(secretToken);

        var permissions = Arrays.asList(user.getPermission().equals("N") ? "USUARIO" : "ADMIN");

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create()
                .withExpiresAt(expiresIn)
                .withClaim("permissions",permissions)
                .withSubject(user.getId().toString())
                .withIssuer("ecotrackproject")
                .sign(tokenHashed);


        var authenticateDTO = new UserAuthenticationDTO().builder().expiresIn(expiresIn.toEpochMilli()).accessToken(token).permissions(permissions).build();

        return authenticateDTO;


    }
}
