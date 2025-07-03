package tj.alimov.productservice.exception.category;

public class CategoryExistsException extends RuntimeException{
    private String message;
    private Integer code;

    public CategoryExistsException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
    public CategoryExistsException(String message){
        this.message = message;
    }
}
