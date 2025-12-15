package br.com.rianhenrique.ecotrackproject.entities;


import br.com.rianhenrique.ecotrackproject.enums.ReportEnums;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Data
@Entity(name = "REL_INDUSTRIAL")
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REL_ID")
    private Long id;

    @Column(name = "REL_ID_INDUSTRIA",nullable = false)
    private Long idIndustry;
    @Column(name = "REL_DTAENT")
    private LocalDateTime reportDate;
    @Column(name = "REL_CUSTO")
    private double estimatedCost;

    @Column(name = "REL_RESIDUO_GERADO")
    private double wasteGenerated;
    @Column(name = "REL_RESIDUO_TRATADO")
    private double wastreTreated;

    @Enumerated(EnumType.STRING)
    @Column(name = "REL_PROCESSOS")
    private ReportEnums status;
    @CurrentTimestamp
    @Column(name = "REL_DTACAD")
    private LocalDateTime createdAt;

}
