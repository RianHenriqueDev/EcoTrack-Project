package br.com.rianhenrique.ecotrackproject.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthenticationDTO {

    private List<String> permissions;
    private Long expiresIn;
    private String accessToken;
}
