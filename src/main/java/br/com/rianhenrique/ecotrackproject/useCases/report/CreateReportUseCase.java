package br.com.rianhenrique.ecotrackproject.useCases.report;

import br.com.rianhenrique.ecotrackproject.entities.ReportEntity;
import br.com.rianhenrique.ecotrackproject.enums.ReportEnums;
import br.com.rianhenrique.ecotrackproject.repositories.ReportRepository;
import br.com.rianhenrique.ecotrackproject.utils.UtilsFunctions;
import br.com.rianhenrique.ecotrackproject.utils.errors.EntityException;
import jakarta.persistence.EnumType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateReportUseCase {


    @Autowired
    private ReportRepository reportRepository;

    public ReportEntity createReport(ReportEntity reportEntity){

        return this.reportRepository.save(reportEntity);
    }

    public List<ReportEntity> findAllByIdIndustry(Long idIndustry){

        var listInd = this.reportRepository.findByidIndustry(idIndustry);
        return listInd.stream().toList();
    }

    public List<ReportEntity> findBystatus(String status){
        var list = this.reportRepository.findBystatus(ReportEnums.valueOf(status));
        return list.stream().toList();
    }

    public void modifyReportByStatus(Long id,String status){
        var report = this.reportRepository.findById(id).orElseThrow(()-> {
            throw  new EntityException("Relatório não encontrado", HttpStatus.NOT_FOUND);
        });


        if (report.getStatus() == ReportEnums.valueOf(status)){
            throw  new EntityException("Você não pode mudar para o mesmo STATUS!", HttpStatus.CONFLICT);

        }

        report.setStatus(ReportEnums.valueOf(status));


        this.reportRepository.save(report);
    }
}
