package tj.alimov.productservice.exception.product;

public class ProductTypeExistsException extends RuntimeException{
    public ProductTypeExistsException(String message){
        super(message);
    }
}
