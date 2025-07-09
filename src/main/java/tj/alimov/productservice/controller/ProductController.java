package tj.alimov.productservice.controller;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tj.alimov.productservice.exception.user.TokenNotProvidedException;
import tj.alimov.productservice.service.JwtService;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final JwtService service;

    @PostMapping("/token")
    public ResponseEntity<Void> manageToken(@RequestBody String token){
        System.out.println(token);
        Claims claims = service.extractClaims(token);
        System.out.println(claims.get("roles"));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin")
    public ResponseEntity<String> hasPermission(){
        System.out.println("User has role admin");
        return ResponseEntity.ok("Hello");
    }

    @PostMapping("")
    public ResponseEntity<Void> createProduct(HttpServletRequest request){
        String token = retrieveToken(request);

        return ResponseEntity.noContent().build();
    }

    private String retrieveToken(HttpServletRequest request){
        String header = request.getHeader("Authentication");
        if(header == null || !header.startsWith("Bearer")){
            throw new TokenNotProvidedException("Token is not provided");
        }
        String token = header.substring("Bearer ".length());
        return token;
    }
}
