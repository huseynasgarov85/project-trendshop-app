package com.example.projecttrendshopapp.exceptionHandler;

import com.example.projecttrendshopapp.dto.ExceptionDto;
import com.example.projecttrendshopapp.exception.IllegalStateException;
import com.example.projecttrendshopapp.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionDto handleAuthenticatedException(AuthenticationException e) {
        log.error("ActionLog error " + e.getMessage());
        return new ExceptionDto(e.getMessage());
    }

    @ExceptionHandler(InvalidBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleInvalidException(InvalidBalanceException e) {
        log.error("ActionLog is failed: {}", e.getMessage());
        return new ExceptionDto(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleNotFoundException(NotFoundException e) {
        log.error("ActionLog is failed: {}", e.getMessage());
        return new ExceptionDto(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handleException(Exception e) {
        log.error(e.getMessage());
        return new ExceptionDto(e.getMessage());
    }

    @ExceptionHandler(DuplicateUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleDuplicateException(DuplicateUserException e) {
        log.error("ActionLog is failed : {}", e.getMessage());
        return new ExceptionDto(e.getMessage());
    }

    @ExceptionHandler(OutOfStockException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleOutOfStockException(OutOfStockException e) {
        log.error("ActionLog is failed: {}", e.getMessage());
        return new ExceptionDto(e.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleIllegalStateException(IllegalStateException e) {
        log.error("ActionLog is failed:{}", e.getMessage());
        return new ExceptionDto(e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionDto handleAccessDeniedException(AccessDeniedException e) {
        log.error("ActionLog is failed: {}", e.getMessage());
        return new ExceptionDto(e.getMessage());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionDto handleAlreadyExistsException(AlreadyExistsException e) {
        log.error("ActionLog is failed: {}", e.getMessage());
        return new ExceptionDto(e.getMessage());
    }

    @ExceptionHandler(ExpiredCardException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleExpiredCardException(ExpiredCardException e) {
        log.error("ActionLog error " + e.getMessage());
        return new ExceptionDto(e.getMessage());
    }

}
