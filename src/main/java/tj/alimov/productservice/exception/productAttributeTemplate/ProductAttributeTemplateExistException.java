package tj.alimov.productservice.exception.productAttributeTemplate;

import lombok.Data;

@Data
public class ProductAttributeTemplateExistException extends RuntimeException{
    String message;
    Integer code = 409;
    public ProductAttributeTemplateExistException(String message){
        this.message = message;
    }
    public ProductAttributeTemplateExistException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }
}
