package br.com.compass.pb.asynchers.compassflix.exceptions;


import javax.naming.AuthenticationException;

public class movieAlreadyExistAuthenticationException extends AuthenticationException {
    public movieAlreadyExistAuthenticationException(final String msg) {
        super(msg);
    }
}
