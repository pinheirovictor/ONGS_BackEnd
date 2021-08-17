package br.ufc.ong.exception;

public class DataIntegrityException extends RuntimeException{

    public DataIntegrityException(String msg){
        super(msg);
    }
}

