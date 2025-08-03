package tj.alimov.productservice.exception.size;

import lombok.Data;

@Data
public class SizeTypeConflictException extends RuntimeException{
    private String message;
    private Integer code = 409;
    public SizeTypeConflictException(String message){
        this.message = message;
    }
    public SizeTypeConflictException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
}
