package fixando.br.fixando.globalHandler;

import fixando.br.fixando.dto.ErrorResponse;
import fixando.br.fixando.model.exception.NumberInvalidException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// Controller + ResponseBody (allows to use controller and send the exception the message as JSON with ResponseBody)
@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorResponse> handleNumberInvalid(NumberInvalidException ex, HttpServletRequest httpServletRequest){

        ErrorResponse errorResponse = new ErrorResponse(
               HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                httpServletRequest.getRequestURI(),
                Collections.singletonMap(" ", ex.getMessage()),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgument(MethodArgumentNotValidException ex, HttpServletRequest httpServletRequest){

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach( error -> fieldErrors.put(error.getObjectName(), error.getDefaultMessage()));

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                httpServletRequest.getRequestURI(),
                fieldErrors,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
