package br.com.rianhenrique.ecotrackproject.useCases.industry;

import br.com.rianhenrique.ecotrackproject.entities.AddressEntity;
import br.com.rianhenrique.ecotrackproject.entities.IndustryEntity;
import br.com.rianhenrique.ecotrackproject.repositories.AddressRepository;
import br.com.rianhenrique.ecotrackproject.repositories.IndustryRepository;
import br.com.rianhenrique.ecotrackproject.repositories.UserRepository;
import br.com.rianhenrique.ecotrackproject.utils.UtilsFunctions;
import br.com.rianhenrique.ecotrackproject.utils.errors.EntityException;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private AddressRepository addressRepository;

    public AddressEntity createAddress(AddressEntity addressEntity) {
        return addressRepository.save(addressEntity);
    }

    public IndustryEntity createIndustry(IndustryEntity  industryEntity) {


        var industry = this.industryRepository.findByEmailIgnoreCase(industryEntity.getEmail()).orElse(null);


        if (industry == null) {

            return this.industryRepository.save(industryEntity);
        }else {
            if (industry.getEmail() != null || industryEntity.getCnpj() != null) {
                throw new EntityException("Usuário já registrado no banco de dados!", HttpStatus.CONFLICT);
            }
        }

        return null;

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

    public ResponseEntity<IndustryEntity> updateIndustry(Long id, IndustryEntity  industryEntity) {

        var industry = this.industryRepository.findById(id).orElseThrow(()-> {
            throw new EntityException("Industria não encontrada!",HttpStatus.NOT_FOUND);
        });

        BeanUtils.copyProperties(industryEntity, industry, UtilsFunctions.getNameProperties(industryEntity));

        var industrySaved = this.industryRepository.save(industry);

        return ResponseEntity.ok(industrySaved);
    }
}
