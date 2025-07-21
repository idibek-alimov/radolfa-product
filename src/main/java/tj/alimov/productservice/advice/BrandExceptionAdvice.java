package tj.alimov.productservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tj.alimov.productservice.dto.exception.ApiException;
import tj.alimov.productservice.exception.brand.BrandExistsException;
import tj.alimov.productservice.exception.brand.BrandNotFoundException;

@ControllerAdvice
public class BrandExceptionAdvice {
    @ExceptionHandler({BrandNotFoundException.class})
    public ResponseEntity<ApiException> brandNotFoundExceptionHandler(BrandNotFoundException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiException(exception.getMessage(), 400));
    }

    @ExceptionHandler({BrandExistsException.class})
    public ResponseEntity<ApiException> brandExistsException(BrandExistsException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiException(exception.getMessage(), 400));
    }
}
