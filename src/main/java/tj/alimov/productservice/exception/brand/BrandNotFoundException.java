package tj.alimov.productservice.exception.brand;

import lombok.Data;

@Data
public class BrandNotFoundException extends RuntimeException{
    private String message;
    private Integer code;

    public BrandNotFoundException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
    public BrandNotFoundException(String message){
        this(message, 400);
    }
}
