package br.com.rianhenrique.ecotrackproject.controllers;

import br.com.rianhenrique.ecotrackproject.entities.ReportEntity;
import br.com.rianhenrique.ecotrackproject.repositories.ReportRepository;
import br.com.rianhenrique.ecotrackproject.useCases.report.CreateReportUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {


    @Autowired
    private CreateReportUseCase createReportUseCase;



    @PostMapping("")
    @PreAuthorize("hasRole('USUARIO')")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<ReportEntity> createReport(@RequestBody  ReportEntity reportEntity) {

        var report = this.createReportUseCase.createReport(reportEntity);

        return ResponseEntity.ok(report);
    }

    @GetMapping("/industry/{id}")
    @PreAuthorize("hasRole('USUARIO')")
    @SecurityRequirement(name = "jwt_auth")
    public List<ReportEntity> getReport(@PathVariable Long id) {

        return this.createReportUseCase.findAllByIdIndustry(id);
    }


    @GetMapping("/pending")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "jwt_auth")
    public List<ReportEntity> getPendingReport() {
        return this.createReportUseCase.findBystatus("PENDENTE");
    }

    @PutMapping("/approve/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "jwt_auth")
    public void approveReport(@PathVariable Long id) {

        System.out.println("ENTROUUU");
        this.createReportUseCase.modifyReportByStatus(id,"APROVADO");
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "jwt_auth")
    public void rejectReport(@PathVariable Long id) {
        this.createReportUseCase.modifyReportByStatus(id,"REPROVADO");

    }


}
