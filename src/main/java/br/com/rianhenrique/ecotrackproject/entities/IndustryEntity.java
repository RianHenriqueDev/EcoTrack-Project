package br.com.rianhenrique.ecotrackproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Entity(name = "industria")
public class IndustryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REL_DTACAD")
    private Long id;

    @Column(name = "IND_NOME")
    private String name;
    @Column(name = "IND_NOMFAN")
    private String corporateName;
    @Column(name = "IND_C_N_P_J",unique = true)
    private String cnpj;
    @Column(name = "IND_TELEFONE")
    private String phone;
    @Column(name = "IND_EMAIL",unique = true)
    @Email(message = "O e-mail precisa ser válido.")
    private String email;

    @Column(nullable = false,name = "IND_ADDRESS")
    private Long id_address;
}
