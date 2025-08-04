package tj.alimov.productservice.exception.size;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class SizeOptionNotFoundException extends RuntimeException{
    private String message;
    private Integer code = HttpStatus.NOT_FOUND.value();

    public SizeOptionNotFoundException(String message){
        this.message = message;
    }
    public SizeOptionNotFoundException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
}
