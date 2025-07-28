package tj.alimov.productservice.exception.productAttributeTemplate;

import lombok.Data;

@Data
public class ProductAttributeTemplateNotFoundException extends RuntimeException{
    String message;
    Integer code = 404;
    public ProductAttributeTemplateNotFoundException(String message){
        this.message = message;
    }
    public ProductAttributeTemplateNotFoundException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
}
