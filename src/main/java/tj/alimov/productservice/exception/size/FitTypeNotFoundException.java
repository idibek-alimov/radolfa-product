package tj.alimov.productservice.exception.size;

import lombok.Data;

@Data
public class FitTypeNotFoundException extends RuntimeException{
    private String message;
    private Integer code = 404;
    public FitTypeNotFoundException(String message){
        this.message = message;
    }
    public FitTypeNotFoundException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
}
