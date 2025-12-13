package br.com.rianhenrique.ecotrackproject.controllers;

import br.com.rianhenrique.ecotrackproject.entities.IndustryEntity;
import br.com.rianhenrique.ecotrackproject.useCases.industry.IndustryUseCse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/industries")
public class IndustryController {


    @Autowired
    private IndustryUseCse industryUseCse;



    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createIndustry(IndustryEntity  ind) {

        var industry =  this.industryUseCse.createIndustry(ind);

        return ResponseEntity.ok().body(industry);
    }


    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public List<IndustryEntity> findAll() {
        return this.industryUseCse.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<IndustryEntity> findById(@PathVariable(name = "id") Long id) {

        var industry = this.industryUseCse.findIndustryById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(industry);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<IndustryEntity> updateIndustry(@PathVariable(name = "id") Long Id, IndustryEntity  industry) {

        IndustryEntity ind = new IndustryEntity();

        ind.setCnpj(industry.getCnpj());
        ind.setName(industry.getName());
        ind.setEmail(industry.getEmail());
        ind.setPhone(industry.getPhone());

        this.industryUseCse.updateIndustry(ind);

        return ResponseEntity.ok().body(ind);
    }


}
