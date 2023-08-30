package com.spring.produto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class CustomResponseEntityExceptionHandle extends ResponseEntityExceptionHandler {

    private static final Long serialVersionUID = 1L;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponseMessage> handleGlobalExceptions(Exception ex) {

        var statusError = HttpStatus.INTERNAL_SERVER_ERROR;


        return ResponseEntity.status(statusError)
                .body(getCustomResponseMessage(ex, statusError));
    }

    @ExceptionHandler(CategoriaNomeMustBeNotRepeatedException.class)
    public ResponseEntity<CustomResponseMessage> handleCategoriaNomeMustBeNotRepeatedException
            (CategoriaNomeMustBeNotRepeatedException ex) {

        var httpStatus = HttpStatus.BAD_REQUEST;
      return  ResponseEntity.status(httpStatus)
              .body(getCustomResponseMessage(ex, httpStatus));
    }

    @ExceptionHandler(ProdutoNomeMustBeNotRepeatedException.class)
    public ResponseEntity<CustomResponseMessage> handleProdutoNomeMustBeNotRepeatedException
            (ProdutoNomeMustBeNotRepeatedException ex){
        var httpStatus = HttpStatus.NOT_FOUND;

        return ResponseEntity.status(httpStatus).body(getCustomResponseMessage(ex, httpStatus));
    }

    @ExceptionHandler(CategoriaNotFoundException.class)
    public ResponseEntity<CustomResponseMessage> handleCategoriaNotFoundException(CategoriaNotFoundException ex) {
        var httpStatus = HttpStatus.NOT_FOUND;

        return ResponseEntity.status(httpStatus).body(getCustomResponseMessage(ex, httpStatus));
    }


    /* ----------------------------------------------------------------------------- */

    private CustomResponseMessage getCustomResponseMessage(Exception ex, HttpStatus status) {

        return new CustomResponseMessage(
                status.toString(),
                new Date(),
                ex.getMessage(),
                null
        );
    }

    public static CustomResponseMessage getCustomResponseMessage(Errors validations, HttpStatus status) {

        String[] erros = validations.getAllErrors()
                .stream()
                .map(err -> err.getDefaultMessage())
                .toArray(String[]::new);

        return new CustomResponseMessage(
                status.toString(),
                new Date(),
                "",
                erros
        );
    }





}
