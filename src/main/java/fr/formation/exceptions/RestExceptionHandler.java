package fr.formation.exceptions;

import fr.formation.artiste.ArtisteException;
import fr.formation.user.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ Exception.class})
    protected ResponseEntity<CustomErrorResponse> manageException( Exception ex) {

        HttpStatus status;

        if ( (ex instanceof UserException) || (ex instanceof ArtisteException)   ) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        CustomErrorResponse cer = new CustomErrorResponse();
        cer.setError(ex.getMessage());
        return new ResponseEntity<>(cer, status);
    }
}
