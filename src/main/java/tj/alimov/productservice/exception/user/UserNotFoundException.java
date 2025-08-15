package tj.alimov.productservice.exception.user;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UserNotFoundException extends RuntimeException {
    private String message;
    private Integer code = HttpStatus.NOT_FOUND.value();
    public UserNotFoundException(String message){
        this.message = message;
    }

    public UserNotFoundException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
}
