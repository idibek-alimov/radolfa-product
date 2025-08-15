package tj.alimov.productservice.exception.productArticle;

import lombok.Data;

@Data
public class ProductArticleIllegalAccessException extends RuntimeException{
    private String message;
    private Integer code = 401;

    public ProductArticleIllegalAccessException(String message){
        this.message = message;
    }
    public ProductArticleIllegalAccessException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
}
