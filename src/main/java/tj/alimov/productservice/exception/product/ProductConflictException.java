package tj.alimov.productservice.exception.product;


import lombok.Data;

@Data
public class ProductConflictException extends RuntimeException{
    private String message;
    private Integer code = 409;
    public ProductConflictException(String message){
        this.message = message;
    }
    public ProductConflictException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
}
