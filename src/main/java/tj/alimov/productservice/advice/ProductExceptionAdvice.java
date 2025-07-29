package tj.alimov.productservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tj.alimov.productservice.dto.exception.ApiException;
import tj.alimov.productservice.exception.product.ProductNotFoundException;

@ControllerAdvice
public class ProductExceptionAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiException> productNotFoundExceptionHandle(ProductNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiException(exception.getMessage(), exception.getCode()));
    }
}
