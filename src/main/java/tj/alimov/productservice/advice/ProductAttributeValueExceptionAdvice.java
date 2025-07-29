package tj.alimov.productservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tj.alimov.productservice.dto.exception.ApiException;
import tj.alimov.productservice.exception.productAttributeValue.ProductAttributeValueConflictException;
import tj.alimov.productservice.exception.productAttributeValue.ProductAttributeValueNotFoundException;

@ControllerAdvice
public class ProductAttributeValueExceptionAdvice {

    @ExceptionHandler(ProductAttributeValueNotFoundException.class)
    public ResponseEntity<ApiException> productAttributeValueNotFoundHandle(ProductAttributeValueNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiException(exception.getMessage(), exception.getCode()));
    }

    @ExceptionHandler(ProductAttributeValueConflictException.class)
    public ResponseEntity<ApiException> productAttributeValueConflictHandle(ProductAttributeValueConflictException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiException(exception.getMessage(), exception.getCode()));
    }
}
