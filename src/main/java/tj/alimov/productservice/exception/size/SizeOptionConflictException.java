package tj.alimov.productservice.exception.size;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class SizeOptionConflictException extends RuntimeException{
    private String message;
    private Integer code = HttpStatus.CONFLICT.value();
    public SizeOptionConflictException(String message){
        this.message = message;
    }
    public SizeOptionConflictException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
}
