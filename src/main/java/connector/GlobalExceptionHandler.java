package connector;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${result.routeDoesNotExists}")
    private String ROUTE_DOES_NOT_EXISTS;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleConflict(){
        return new ResponseEntity(ROUTE_DOES_NOT_EXISTS, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
