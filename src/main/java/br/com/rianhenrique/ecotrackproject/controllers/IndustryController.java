package br.com.rianhenrique.ecotrackproject.controllers;

import br.com.rianhenrique.ecotrackproject.entities.AddressEntity;
import br.com.rianhenrique.ecotrackproject.entities.IndustryEntity;
import br.com.rianhenrique.ecotrackproject.useCases.industry.IndustryUseCse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> createIndustry(@RequestBody IndustryEntity  ind) {

        System.out.println("Creando industry " + ind);
        var industry =  this.industryUseCse.createIndustry(ind);

        return ResponseEntity.ok().body(industry);
    }

    @PostMapping("/address")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> createAddress(@RequestBody AddressEntity addressEntity) {
        this.industryUseCse.createAddress(addressEntity);

        return ResponseEntity.ok().body(addressEntity);
    }


    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "jwt_auth")
    public List<IndustryEntity> findAll() {
        return this.industryUseCse.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<IndustryEntity> findById(@PathVariable(name = "id") Long id) {

        var industry = this.industryUseCse.findIndustryById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(industry);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<IndustryEntity> updateIndustry(@PathVariable(name = "id") Long id, @RequestBody IndustryEntity  industry) {


        this.industryUseCse.updateIndustry(id,industry);

        return ResponseEntity.ok().body(industry);
    }


}
