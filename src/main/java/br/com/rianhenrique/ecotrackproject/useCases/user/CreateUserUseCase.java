package br.com.rianhenrique.ecotrackproject.useCases.user;

import br.com.rianhenrique.ecotrackproject.entities.UserEntity;
import br.com.rianhenrique.ecotrackproject.repositories.UserRepository;
import br.com.rianhenrique.ecotrackproject.utils.errors.EntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void createUser(UserEntity user){

        var userExists = this.userRepository.findByEmailIgnoreCase(user.getEmail());


        if(userExists.isPresent()) {
            throw new EntityException("O e-mail já existe no banco de dados!", HttpStatus.CONFLICT);
        }

        var hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);

        this.userRepository.save(user);


    }
}
