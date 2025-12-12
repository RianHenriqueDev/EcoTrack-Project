package br.com.rianhenrique.ecotrackproject.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "endereco")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "END_ID")
    private Long id;
    @Column(name = "END_ENDNOME")
    private String address;
    @Column(name = "END_CIDADE")
    private String city;
    @Column(name = "END_ESTADO")
    private String state;
    @Column(name = "END_C_E_P")
    private String cep;
    @Column(name = "END_COMPLEMENTO")
    private String complement;
    @Column(name = "END_BAIRRO")
    private String district;


}
