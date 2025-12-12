package br.com.rianhenrique.ecotrackproject.utils.errors;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError {

    private Instant status;
    private Integer code;
    private String error;
    private String message;
    private String path;


}


