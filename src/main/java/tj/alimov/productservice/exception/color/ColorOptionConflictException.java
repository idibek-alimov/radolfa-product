package tj.alimov.productservice.exception.color;

import lombok.Data;

@Data
public class ColorOptionConflictException extends RuntimeException{
    private String message;
    private Integer code = 409;
    public ColorOptionConflictException(String message){
        this.message = message;
    }
    public ColorOptionConflictException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
}
