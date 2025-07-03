package tj.alimov.productservice.exception.category;

public class CategoryDoesNotExistException extends RuntimeException{
    private String message;
    private Integer code;

    public CategoryDoesNotExistException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
    public CategoryDoesNotExistException(String message){
        this.message = message;
    }
}
