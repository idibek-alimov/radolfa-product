package tj.alimov.productservice.exception.user;

public class TokenNotProvidedException extends RuntimeException{
    public TokenNotProvidedException(String message){
        super(message);
    }
}
