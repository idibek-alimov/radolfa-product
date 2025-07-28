package tj.alimov.productservice.exception.productType;

import lombok.Data;

@Data
public class ProductTypeExistsException extends RuntimeException{
    String message;
    Integer code = 409;
    public ProductTypeExistsException(String message){
        this.message = message;
    }
    public ProductTypeExistsException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
}
