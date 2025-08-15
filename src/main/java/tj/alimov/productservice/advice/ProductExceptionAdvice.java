package tj.alimov.productservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tj.alimov.productservice.dto.exception.ApiException;
import tj.alimov.productservice.exception.product.ProductConflictException;
import tj.alimov.productservice.exception.product.ProductIllegalAccessException;
import tj.alimov.productservice.exception.product.ProductNotFoundException;

@RestControllerAdvice
public class ProductExceptionAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiException> productNotFoundExceptionHandle(ProductNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiException(exception.getMessage(), exception.getCode()));
    }
    @ExceptionHandler(ProductConflictException.class)
    public ResponseEntity<ApiException> productConflictExceptionHandle(ProductConflictException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiException(exception.getMessage(), exception.getCode()));
    }
    @ExceptionHandler(ProductIllegalAccessException.class)
    public ResponseEntity<ApiException> productIllegalAccessExceptionHandle(ProductIllegalAccessException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiException(exception.getMessage(), exception.getCode()));
    }
}
