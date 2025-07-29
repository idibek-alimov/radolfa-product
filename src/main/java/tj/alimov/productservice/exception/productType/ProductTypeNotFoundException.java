package tj.alimov.productservice.exception.productType;

import lombok.Data;

@Data
public class ProductTypeNotFoundException extends RuntimeException{
    String message;
    Integer code = 404;
    public ProductTypeNotFoundException(String message){
        this.message = message;
    }
    public ProductTypeNotFoundException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
}
