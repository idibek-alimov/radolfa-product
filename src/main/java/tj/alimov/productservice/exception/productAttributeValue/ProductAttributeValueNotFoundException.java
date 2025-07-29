package tj.alimov.productservice.exception.productAttributeValue;

import lombok.Data;

@Data
public class ProductAttributeValueNotFoundException extends RuntimeException{
    private String message;
    private Integer code = 404;
    public ProductAttributeValueNotFoundException(String message){
        this.message = message;
    }
    public ProductAttributeValueNotFoundException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
}
