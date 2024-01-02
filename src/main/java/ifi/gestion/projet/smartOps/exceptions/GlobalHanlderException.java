package ifi.gestion.projet.smartOps.exceptions;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;


import ifi.gestion.projet.smartOps.dto.response.ApiErrorResponse;
import ifi.gestion.projet.smartOps.dto.responses.validations.ValidationErrorMessage;
import ifi.gestion.projet.smartOps.exceptions.AccountLockedException;
import ifi.gestion.projet.smartOps.exceptions.AlreadyExistsException;
import ifi.gestion.projet.smartOps.exceptions.PasswordsNotMatchException;
import ifi.gestion.projet.smartOps.exceptions.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;


@RestControllerAdvice
@Slf4j
public class GlobalHanlderException {
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(MethodNotAllowedException.class)
    public ApiErrorResponse handleMethodNotAllowedResponse(MethodNotAllowedException e) {
        HttpStatus httpStatus = HttpStatus.METHOD_NOT_ALLOWED;
        return ApiErrorResponse
                .builder()
                .message(e.getMessage())
                .error_code(String.valueOf(httpStatus.value()))
                .status(httpStatus)
                .timeStamp(LocalDateTime.now(ZoneOffset.UTC))
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyExistsException.class)
    public ApiErrorResponse handleAlreadyExistsException(AlreadyExistsException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ApiErrorResponse
                .builder()
                .message(e.getMessage())
                .error_code(String.valueOf(httpStatus.value()))
                .status(httpStatus)
                .timeStamp(LocalDateTime.now(ZoneOffset.UTC))
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiErrorResponse handleResourceNotFoundException(ResourceNotFoundException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return ApiErrorResponse
                .builder()
                .message(e.getMessage())
                .error_code(String.valueOf(httpStatus.value()))
                .status(httpStatus)
                .timeStamp(LocalDateTime.now(ZoneOffset.UTC))
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordsNotMatchException.class)
    public ApiErrorResponse handlePasswordsNotMatchException(PasswordsNotMatchException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ApiErrorResponse
                .builder()
                .message(e.getMessage())
                .error_code(String.valueOf(httpStatus.value()))
                .status(httpStatus)
                .timeStamp(LocalDateTime.now(ZoneOffset.UTC))
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ValidationErrorMessage> handleInvalidArgument(MethodArgumentNotValidException ex) {
        List<ValidationErrorMessage> validationErrorMessages = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> validationErrorMessages.add(
                new ValidationErrorMessage(error.getField(), error.getDefaultMessage())));
        return validationErrorMessages;
    }

    @ResponseStatus(HttpStatus.LOCKED)
    @ExceptionHandler(AccountLockedException.class)
    public ApiErrorResponse handleAccountLockedException(AccountLockedException e) {
        HttpStatus httpStatus = HttpStatus.LOCKED;
        return ApiErrorResponse
                .builder()
                .message(e.getMessage())
                .error_code(String.valueOf(httpStatus.value()))
                .status(httpStatus)
                .timeStamp(LocalDateTime.now(ZoneOffset.UTC))
                .build();
    }
}
