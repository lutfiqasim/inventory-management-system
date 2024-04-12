package bzu.api.inventoryManagmentSystem.controller.handler;

import bzu.api.inventoryManagmentSystem.exception.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class ValidationExceptionHandler {

    public static ResponseEntity<Object> validate(BindingResult result, String entity) {
        Map<String, Object> errorMap = new HashMap<>();
        for (FieldError error : result.getFieldErrors()) {
            errorMap.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest()
                .body(new BadRequestException("Validation failed", entity, errorMap.toString()).toString());
    }
}
