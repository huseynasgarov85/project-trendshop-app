package com.example.projecttrendshopapp.exception;

public class ExpiredCardException extends RuntimeException{
    public ExpiredCardException(String message){
        super(message);
    }
}
