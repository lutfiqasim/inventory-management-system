package bzu.api.inventoryManagmentSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    private String resourceName;
    private String fieldName;

    public BadRequestException(String message, String resourceName, String fieldName) {
        super(String.format("%s:  %s has wrong %s value'", message,resourceName, fieldName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return "BadRequestException{" +
                "resourceName='" + resourceName + '\'' +
                ", fieldName='" + fieldName + '\'' +
                '}';
    }
//    private static Map<String, Object> getParameters(String resourceName, String fieldName) {
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("message", "error." + fieldName);
//        parameters.put("params", resourceName);
//        return parameters;
//    }

}
