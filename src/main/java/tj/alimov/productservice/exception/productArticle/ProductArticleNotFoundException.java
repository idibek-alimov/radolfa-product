package tj.alimov.productservice.exception.productArticle;

import lombok.Data;

@Data
public class ProductArticleNotFoundException extends RuntimeException{
    private String message;
    private Integer code = 404;

    public ProductArticleNotFoundException(String message){
        this.message = message;
    }
    public ProductArticleNotFoundException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
}
