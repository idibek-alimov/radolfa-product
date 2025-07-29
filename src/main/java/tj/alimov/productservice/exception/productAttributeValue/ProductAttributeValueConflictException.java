package tj.alimov.productservice.exception.productAttributeValue;

import lombok.Data;

@Data
public class ProductAttributeValueConflictException extends RuntimeException{
    private String message;
    private Integer code = 409;

    public ProductAttributeValueConflictException(String message){
        this.message = message;
    }

    public ProductAttributeValueConflictException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
}
