package tj.alimov.productservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tj.alimov.productservice.dto.exception.ApiException;
import tj.alimov.productservice.exception.productPriceHistory.ProductPriceHistoryNotFoundException;

@RestControllerAdvice
public class ProductPriceHistoryExceptionAdvice {

    @ExceptionHandler(ProductPriceHistoryNotFoundException.class)
    public ResponseEntity<ApiException> productPriceHistoryNotFoundExceptionHandle(ProductPriceHistoryNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiException(exception.getMessage(), exception.getCode()));
    }
}
