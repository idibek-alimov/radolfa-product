package tj.alimov.productservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tj.alimov.productservice.dto.exception.ApiException;
import tj.alimov.productservice.exception.user.TokenNotProvidedException;
import tj.alimov.productservice.exception.user.UserNotFoundException;

@RestControllerAdvice
public class UserExceptionAdvice {
    @ExceptionHandler(TokenNotProvidedException.class)
    public ResponseEntity<ApiException> tokenNotProvidedExceptionHandle(TokenNotProvidedException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiException(exception.getMessage(), exception.getCode()));
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiException> userNotFoundExceptionHandle(UserNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiException(exception.getMessage(), exception.getCode()));
    }
}
