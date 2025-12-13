package br.com.rianhenrique.ecotrackproject.useCases.industry;

import br.com.rianhenrique.ecotrackproject.entities.IndustryEntity;
import br.com.rianhenrique.ecotrackproject.repositories.IndustryRepository;
import br.com.rianhenrique.ecotrackproject.repositories.UserRepository;
import br.com.rianhenrique.ecotrackproject.utils.errors.EntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndustryUseCse {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IndustryRepository industryRepository;

    public IndustryEntity createIndustry(IndustryEntity  industryEntity) {

        var industry = this.industryRepository.findByEmailIgnoreCase(industryEntity.getEmail()).orElseThrow();


        if(industry.getEmail() != null || industryEntity.getCnpj() != null) {
            throw new EntityException("Usuário já registrado no banco de dados!", HttpStatus.CONFLICT);
        }


        return this.industryRepository.save(industry);


    }


    public List<IndustryEntity> findAll() {
        return this.industryRepository.findAll();
    }

    public IndustryEntity findIndustryById(Long id) {
       var industry = this.industryRepository.findById(id).orElseThrow(()-> {
           throw new EntityException("Industria não encontrada!",HttpStatus.NOT_FOUND);
       });

       return industry;
    }

    public ResponseEntity<IndustryEntity> updateIndustry(IndustryEntity  industryEntity) {

        var industry = this.industryRepository.findById(industryEntity.getId()).orElseThrow(()-> {
            throw new EntityException("Industria não encontrada!",HttpStatus.NOT_FOUND);
        });

        this.industryRepository.save(industry);

        return ResponseEntity.ok(industry);
    }
}
