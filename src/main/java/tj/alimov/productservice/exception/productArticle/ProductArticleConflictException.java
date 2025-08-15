package tj.alimov.productservice.exception.productArticle;

import lombok.Data;

@Data
public class ProductArticleConflictException extends RuntimeException{
    private String message;
    private Integer code = 409;

    public ProductArticleConflictException(String message){
        this.message = message;
    }
    public ProductArticleConflictException(String message, Integer code){
        this.message = message;
        this.code = code;
    }

}
