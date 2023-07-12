package br.com.compass.pb.asynchers.compassflix.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class MovieAlreadyExistException extends RuntimeException {
    public MovieAlreadyExistException(String message) {
        super(message);
    }

}
