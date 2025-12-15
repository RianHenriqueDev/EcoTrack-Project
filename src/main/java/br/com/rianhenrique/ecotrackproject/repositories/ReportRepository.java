package br.com.rianhenrique.ecotrackproject.repositories;

import br.com.rianhenrique.ecotrackproject.entities.ReportEntity;
import br.com.rianhenrique.ecotrackproject.enums.ReportEnums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<ReportEntity,Long> {

    Optional<ReportEntity> findById(Long id);
    List<ReportEntity> findByidIndustry(Long idIndustry);

    List<ReportEntity> findBystatus(ReportEnums status);

}
