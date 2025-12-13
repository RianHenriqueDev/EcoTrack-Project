package br.com.rianhenrique.ecotrackproject.utils.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldMessage {

    private String field;
    private String message;

}
