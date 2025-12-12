package br.com.rianhenrique.ecotrackproject.utils.errors;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Data
public class EntityException extends RuntimeException {


    @Setter(AccessLevel.NONE)
    private HttpStatus status;

    public EntityException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }


}
