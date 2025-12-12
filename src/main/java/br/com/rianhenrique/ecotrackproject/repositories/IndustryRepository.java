package br.com.rianhenrique.ecotrackproject.repositories;

import br.com.rianhenrique.ecotrackproject.entities.IndustryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IndustryRepository extends JpaRepository<IndustryEntity,Long> {

    Optional<IndustryEntity> findByName(String name);
    Optional<IndustryEntity> findById(Long id);

}
