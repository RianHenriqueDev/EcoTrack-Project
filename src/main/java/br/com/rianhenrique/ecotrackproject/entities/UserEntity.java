package br.com.rianhenrique.ecotrackproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "USUARIO")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USU_ID")
    private Long id;
    @Column(name = "USU_NAME")
    private String name;

    @Email(message = "O e-mail precisa ser válido.")
    @Column(name = "USU_EMAIL")
    private String email;
    @Column(name = "USU_SENHA")
    private String password;

    @Column(name = "USU_PERMISSAO",columnDefinition = "CHAR(1) DEFAULT 'N'")
    private String permission;
}

