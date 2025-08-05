package tj.alimov.productservice.exception.size;

import lombok.Data;

@Data
public class SizeTypeNotFoundException extends RuntimeException{
    private String message;
    private Integer code = 404;

    public SizeTypeNotFoundException(String message){
        this.message = message;
    }
    public SizeTypeNotFoundException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
}
