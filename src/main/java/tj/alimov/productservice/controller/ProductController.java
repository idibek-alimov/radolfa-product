package tj.alimov.productservice.controller;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
}
