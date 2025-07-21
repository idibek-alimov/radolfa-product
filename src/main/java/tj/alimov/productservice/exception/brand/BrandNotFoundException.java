package tj.alimov.productservice.exception.brand;

public class BrandNotFoundException extends RuntimeException{
    private String message;
    private Integer code;

    public BrandNotFoundException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
    public BrandNotFoundException(String message){
        this.message = message;
    }
}
