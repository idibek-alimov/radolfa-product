package tj.alimov.productservice.exception.color;

import lombok.Data;

@Data
public class ColorOptionNotFoundException extends RuntimeException{
    private String message;
    private Integer code = 404;

    public ColorOptionNotFoundException(String message){
        this.message = message;
    }
    public ColorOptionNotFoundException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
}
