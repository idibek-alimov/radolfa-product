package tj.alimov.productservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tj.alimov.productservice.dto.exception.ApiException;
import tj.alimov.productservice.exception.productArticle.ProductArticleConflictException;
import tj.alimov.productservice.exception.productArticle.ProductArticleIllegalAccessException;
import tj.alimov.productservice.exception.productArticle.ProductArticleNotFoundException;

@RestControllerAdvice
public class ProductArticleExceptionAdvice {

    @ExceptionHandler(ProductArticleNotFoundException.class)
    public ResponseEntity<ApiException> productArticleNotFoundExceptionHandle(ProductArticleNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiException(exception.getMessage(), exception.getCode()));
    }

    @ExceptionHandler(ProductArticleConflictException.class)
    public ResponseEntity<ApiException> productArticleConflictExceptionHandle(ProductArticleConflictException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiException(exception.getMessage(), exception.getCode()));
    }

    @ExceptionHandler(ProductArticleIllegalAccessException.class)
    public ResponseEntity<ApiException> productArticleIllegalAccessExceptionHandle(ProductArticleIllegalAccessException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiException(exception.getMessage(), exception.getCode()));
    }
}
