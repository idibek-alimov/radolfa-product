package tj.alimov.productservice.exception.product;


public class ProductUpdateException extends RuntimeException{
    public ProductUpdateException(String message){
        super(message);
    }
}
