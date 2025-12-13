package br.com.rianhenrique.ecotrackproject.repositories;

import br.com.rianhenrique.ecotrackproject.entities.IndustryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IndustryRepository extends JpaRepository<IndustryEntity,Long> {

    Optional<IndustryEntity> findByNameIgnoreCase(String name);
    Optional<IndustryEntity> findByEmailIgnoreCase(String email);
    Optional<IndustryEntity> findByCnpj(String phone);
    Optional<IndustryEntity> findById(Long id);

}
