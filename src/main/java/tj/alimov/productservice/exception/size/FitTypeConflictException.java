package tj.alimov.productservice.exception.size;

import lombok.Data;

@Data
public class FitTypeConflictException extends RuntimeException{
    private String message;
    private Integer code = 409;
    public FitTypeConflictException(String message){
        this.message = message;
    }

    public FitTypeConflictException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
}
