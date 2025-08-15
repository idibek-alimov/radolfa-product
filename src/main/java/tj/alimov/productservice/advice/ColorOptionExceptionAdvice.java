package tj.alimov.productservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tj.alimov.productservice.dto.exception.ApiException;
import tj.alimov.productservice.exception.color.ColorOptionConflictException;
import tj.alimov.productservice.exception.color.ColorOptionNotFoundException;

@RestControllerAdvice
public class ColorOptionExceptionAdvice{

    @ExceptionHandler(ColorOptionNotFoundException.class)
    public ResponseEntity<ApiException> colorOptionNotFoundExceptionHandle(ColorOptionNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiException(exception.getMessage(), exception.getCode()));
    }

    @ExceptionHandler(ColorOptionConflictException.class)
    public ResponseEntity<ApiException> colorOptionConflictExceptionHandle(ColorOptionConflictException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiException(exception.getMessage(), exception.getCode()));
    }
}
