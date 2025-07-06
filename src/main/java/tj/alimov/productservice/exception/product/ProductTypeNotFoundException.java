package tj.alimov.productservice.exception.product;

public class ProductTypeNotFoundException extends RuntimeException{
    public ProductTypeNotFoundException(String message){
        super(message);
    }
}
