package tj.alimov.productservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tj.alimov.productservice.controller.ProductAttributeTemplateController;
import tj.alimov.productservice.dto.exception.ApiException;
import tj.alimov.productservice.exception.productAttributeTemplate.ProductAttributeTemplateExistException;
import tj.alimov.productservice.exception.productAttributeTemplate.ProductAttributeTemplateNotFoundException;

@ControllerAdvice(assignableTypes = ProductAttributeTemplateController.class)
public class ProductAttributeTemplateExceptionAdvice {
    @ExceptionHandler(ProductAttributeTemplateExistException.class)
    public ResponseEntity<ApiException> productAttributeTemplateExistsExceptionHandler(ProductAttributeTemplateExistException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiException(exception.getMessage(), exception.getCode()));
    }

    @ExceptionHandler(ProductAttributeTemplateNotFoundException.class)
    public ResponseEntity<ApiException> productAttributeTemplateNotFoundExceptionHandler(ProductAttributeTemplateNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiException(exception.getMessage(), exception.getCode()));
    }
}
