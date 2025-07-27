package tj.alimov.productservice;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients
public class ProductServiceApplication {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
//        System.setProperty("DISCOVERY_SERVER_HOST", dotenv.get("DISCOVERY_SERVER_HOST"));
//        System.setProperty("SPRING_DATASOURCE_URL", dotenv.get("SPRING_DATASOURCE_URL"));
//        System.setProperty("SPRING_DATASOURCE_USERNAME", dotenv.get("SPRING_DATASOURCE_USERNAME"));
//        System.setProperty("SPRING_DATASOURCE_PASSWORD", dotenv.get("SPRING_DATASOURCE_PASSWORD"));
//        System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
//
//        System.setProperty("GOOGLE_CREDENTIALS_PATH", dotenv.get("GOOGLE_CREDENTIALS_PATH"));
//        System.setProperty("GOOGLE_REDIRECT_URI", dotenv.get("GOOGLE_REDIRECT_URI"));
//        System.setProperty("GOOGLE_DRIVE_SCOPE", dotenv.get("GOOGLE_DRIVE_SCOPE"));
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}
