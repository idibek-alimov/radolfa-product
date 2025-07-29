package tj.alimov.productservice.exception.product;

public class ProductIllegalAccessException extends RuntimeException{
    public ProductIllegalAccessException(String message){
        super(message);
    }
}
