package br.com.rianhenrique.ecotrackproject.repositories;

import br.com.rianhenrique.ecotrackproject.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findById(Long id);

}
