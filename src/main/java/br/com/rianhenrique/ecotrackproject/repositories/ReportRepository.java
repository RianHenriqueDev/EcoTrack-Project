package br.com.rianhenrique.ecotrackproject.repositories;

import br.com.rianhenrique.ecotrackproject.entities.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<ReportEntity,Long> {

    Optional<ReportEntity> findById(Long id);

}
