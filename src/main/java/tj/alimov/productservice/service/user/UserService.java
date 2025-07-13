package tj.alimov.productservice.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tj.alimov.productservice.exception.user.UserNotFoundException;
import tj.alimov.productservice.feign.UserServiceClient;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserServiceClient userServiceClient;

    public void validateUser(Long id){
        boolean exists = userServiceClient.existsUserById(id);
        if(!exists){
            throw new UserNotFoundException("User with given id was not found");
        }
    }
}
