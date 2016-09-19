package com.catfacts.app.exception;

import java.io.IOException;

/**
 * Created by jose.rubalcaba on 19/09/2016.
 */
public class CatFactException extends IOException{
    public CatFactException(){

    }

    public CatFactException(String message){
        super(message);
    }
}
