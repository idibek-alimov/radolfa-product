package tj.alimov.productservice.exception.product;

import lombok.Data;

@Data
public class ProductNotFoundException extends RuntimeException{
    private String message;
    private Integer code = 404;
    public ProductNotFoundException(String message){
        this.message = message;
    }
    public ProductNotFoundException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
}
