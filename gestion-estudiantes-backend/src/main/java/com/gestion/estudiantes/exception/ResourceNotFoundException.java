package com.gestion.estudiantes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//ESTA CLASE SE USA PARA PRESENTAR EXCEPCIONES (ERRORES)
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
  
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message){
       super(message);
    }
}
