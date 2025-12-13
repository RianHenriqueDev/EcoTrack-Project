package br.com.rianhenrique.ecotrackproject.controllers;

import br.com.rianhenrique.ecotrackproject.DTOs.UserAuthenticationDTO;
import br.com.rianhenrique.ecotrackproject.entities.UserEntity;
import br.com.rianhenrique.ecotrackproject.useCases.user.AuthenticateUserUseCase;
import br.com.rianhenrique.ecotrackproject.useCases.user.CreateUserUseCase;
import br.com.rianhenrique.ecotrackproject.utils.errors.EntityException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {


    @Autowired
    private CreateUserUseCase createUserUseCase;
    
    @Autowired
    private AuthenticateUserUseCase authenticateUserUseCase;



    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@Valid @RequestBody UserEntity user){


        if(user.getEmail().isEmpty()) {
            new EntityException("O campo e-mail está sem dados!", HttpStatus.EXPECTATION_FAILED);
        }else if(user.getName().isEmpty()) {
            new EntityException("O campo password está sem dados!", HttpStatus.EXPECTATION_FAILED);
        }



        this.createUserUseCase.createUser(user);

        return ResponseEntity.ok().body(user);
    }

    // O @RequestBody, sempre retorna o OBJETO inteiro, deve-se criar o DTO com o objeto para poder EXTRAIR cada campo.
    
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody authenticateUserUseCase authenticateUserUseCase){



        var tokenGenerated = this.authenticateUserUseCase.authUser(authenticateUserUseCase);
        return ResponseEntity.ok().body(tokenGenerated);
    }


public record authenticateUserUseCase(String email, String password) {}

}
