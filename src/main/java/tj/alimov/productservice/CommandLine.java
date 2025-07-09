package tj.alimov.productservice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tj.alimov.productservice.feign.UserServiceClient;

@Component
@RequiredArgsConstructor
public class CommandLine implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
//        userServiceClient.existsUserById(1L);
    }
}
