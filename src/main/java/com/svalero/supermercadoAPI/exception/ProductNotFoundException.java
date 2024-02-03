package com.svalero.supermercadoAPI.exception;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(){
        super();
    }

    public ProductNotFoundException(String message){
        super(message);
    }

    public ProductNotFoundException(long id){
        super("El recurso " + id + " no ha sido encontrado.");
    }
}