package com.example.projecttrendshopapp.exception;

import org.aspectj.weaver.ast.Not;
import org.springframework.data.crossstore.ChangeSetPersister;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
