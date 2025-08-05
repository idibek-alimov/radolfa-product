package tj.alimov.productservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tj.alimov.productservice.dto.exception.ApiException;
import tj.alimov.productservice.exception.size.*;

@ControllerAdvice
public class SizeExceptionAdvice {

    // FitType
    @ExceptionHandler(FitTypeNotFoundException.class)
    public ResponseEntity<ApiException> fitTypeNotFoundExceptionHandle(FitTypeNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiException(exception.getMessage(), exception.getCode()));
    }
    @ExceptionHandler(FitTypeConflictException.class)
    public ResponseEntity<ApiException> fitTypeConflictExceptionHandle(FitTypeConflictException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiException(exception.getMessage(), exception.getCode()));
    }


    // SizeType
    @ExceptionHandler(SizeTypeNotFoundException.class)
    public ResponseEntity<ApiException> sizeTypeNotFoundExceptionHandle(SizeTypeNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiException(exception.getMessage(), exception.getCode()));
    }
    @ExceptionHandler(SizeTypeConflictException.class)
    public ResponseEntity<ApiException> sizeTypeConflictExceptionHandle(SizeTypeConflictException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiException(exception.getMessage(), exception.getCode()));
    }

    // SizeOption
    @ExceptionHandler(SizeOptionNotFoundException.class)
    public ResponseEntity<ApiException> sizeOptionNotFoundExceptionHandle(SizeOptionNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiException(exception.getMessage(), exception.getCode()));
    }
    @ExceptionHandler(SizeOptionConflictException.class)
    public ResponseEntity<ApiException> sizeOptionConflictExceptionHandle(SizeOptionConflictException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiException(exception.getMessage(), exception.getCode()));
    }
}
