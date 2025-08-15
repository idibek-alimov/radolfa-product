package tj.alimov.productservice.exception.productPriceHistory;

import lombok.Data;

@Data
public class ProductPriceHistoryNotFoundException extends RuntimeException{
    private String message;
    private Integer code = 404;
    public ProductPriceHistoryNotFoundException(String message){
        this.message = message;
    }
    public ProductPriceHistoryNotFoundException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
}
