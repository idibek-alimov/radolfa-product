package tj.alimov.productservice.exception.product;

import lombok.Data;

@Data
public class ProductIllegalAccessException extends RuntimeException{
    private String message;
    private Integer code = 401;
    public ProductIllegalAccessException(String message){
        this.message = message;
    }
    public ProductIllegalAccessException(String message, Integer code){
        this.message = message;
        this.code = code;
    }


}
