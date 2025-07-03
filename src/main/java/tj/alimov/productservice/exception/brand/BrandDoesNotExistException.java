package tj.alimov.productservice.exception.brand;

public class BrandDoesNotExistException extends RuntimeException{
    private String message;
    private Integer code;

    public BrandDoesNotExistException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
    public BrandDoesNotExistException(String message){
        this.message = message;
    }
}
