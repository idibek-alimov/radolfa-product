package tj.alimov.productservice.exception.category;

public class CategoryExistsException extends RuntimeException{

    public CategoryExistsException(String message){
        super(message);
    }
}
