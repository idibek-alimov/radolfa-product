package tj.alimov.productservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tj.alimov.productservice.dto.exception.ApiException;
import tj.alimov.productservice.exception.productType.ProductTypeExistsException;
import tj.alimov.productservice.exception.productType.ProductTypeNotFoundException;

@RestControllerAdvice()
public class ProductTypeExceptionAdvice {

    @ExceptionHandler(ProductTypeExistsException.class)
    public ResponseEntity<ApiException> productTypeExistsExceptionHandle(ProductTypeExistsException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiException(exception.getMessage(), exception.getCode()));
    }

    @ExceptionHandler(ProductTypeNotFoundException.class)
    public ResponseEntity<ApiException> productTypeNotFoundExceptionHandle(ProductTypeNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiException(exception.getMessage(), exception.getCode()));
    }
}
