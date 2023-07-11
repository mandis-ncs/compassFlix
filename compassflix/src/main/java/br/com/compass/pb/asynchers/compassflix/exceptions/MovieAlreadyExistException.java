package br.com.compass.pb.asynchers.compassflix.exceptions;


import javax.naming.AuthenticationException;

public class MovieAlreadyExistException extends AuthenticationException {
    public MovieAlreadyExistException(final String msg) {
        super(msg);
    }
}
