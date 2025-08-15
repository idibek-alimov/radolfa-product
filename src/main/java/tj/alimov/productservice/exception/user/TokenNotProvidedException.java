package tj.alimov.productservice.exception.user;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class TokenNotProvidedException extends RuntimeException{
    private String message;
    private Integer code = HttpStatus.UNAUTHORIZED.value();
    public TokenNotProvidedException(String message){
        this.message = message;
    }
    public TokenNotProvidedException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
}
