package br.com.compass.pb.asynchers.compassflix.exceptions.handler;
import br.com.compass.pb.asynchers.compassflix.exceptions.ExceptionResponse;
import br.com.compass.pb.asynchers.compassflix.exceptions.ListIsEmptyException;
import br.com.compass.pb.asynchers.compassflix.exceptions.MovieAlreadyExistException;
import br.com.compass.pb.asynchers.compassflix.exceptions.MovieNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handlerException(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDateTime.now(),
                e.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MovieAlreadyExistException.class)
    public final ResponseEntity<ExceptionResponse> handlerMovieAlreadyExistException(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDateTime.now(),
                e.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handlerMovieNotFoundException(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDateTime.now(),
                e.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ListIsEmptyException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDateTime.now(),
                "Total of errors: " + e.getErrorCount() +
                ". Find error: " + e.getFieldError().getDefaultMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);

    }

}
