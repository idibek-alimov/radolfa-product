package tj.alimov.productservice.advice;

import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tj.alimov.productservice.dto.exception.ApiException;
import tj.alimov.productservice.exception.category.CategoryExistsException;
import tj.alimov.productservice.exception.category.CategoryNotFoundException;

@RestControllerAdvice
@ResponseBody
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CategoryExceptionAdvice {

    @ExceptionHandler({CategoryNotFoundException.class})
    public ResponseEntity<ApiException> handleCategoryNotFoundException(CategoryNotFoundException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiException(exception.getMessage(), 400));
    }

    @ExceptionHandler({CategoryExistsException.class})
    public ResponseEntity<ApiException> handleCategoryExistsException(CategoryExistsException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiException(exception.getMessage(), 400));
    }
}
