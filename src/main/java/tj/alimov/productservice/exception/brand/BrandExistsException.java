package tj.alimov.productservice.exception.brand;

public class BrandExistsException extends RuntimeException{
    private String message;
    private Integer code;

    public BrandExistsException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
    public BrandExistsException(String message){
        this.message = message;
    }
}
