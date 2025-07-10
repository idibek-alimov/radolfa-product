package tj.alimov.productservice.exception.product;

import tj.alimov.productservice.dto.product.request.ProductUpdateRequest;

public class ProductUpdateException extends RuntimeException{
    public ProductUpdateException(String message){
        super(message);
    }
}
