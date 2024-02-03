package com.svalero.supermercadoAPI.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(){
        super();
    }

    public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException(long id){
        super("El usuario " + id + " no existe.");
    }
}
