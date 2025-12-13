package br.com.rianhenrique.ecotrackproject.utils.errors;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.List;

@Data
public class EntityException extends RuntimeException {


    @Setter(AccessLevel.NONE)
    private HttpStatus status;

    private List<FieldMessage> fieldErrors;


    public EntityException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public void addError(String field, String message) {
        this.fieldErrors.add(new FieldMessage(field,message));
    }

    public List<FieldMessage> getFieldErrors() {
        return fieldErrors;
    }
}
