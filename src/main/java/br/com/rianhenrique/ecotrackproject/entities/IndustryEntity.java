package br.com.rianhenrique.ecotrackproject.entities;

import jakarta.persistence.*;
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
    @Column(name = "IND_C_N_P_J")
    private String cnpj;
    @Column(name = "IND_TELEFONE")
    private String phone;
    @Column(name = "IND_EMAIL")
    private String email;

    @Column(nullable = false,name = "IND_ADDRESS")
    private Long id_address;
}
