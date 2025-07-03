package tj.alimov.productservice.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tj.alimov.productservice.service.JwtService;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader("Authentication");
        if(header == null || !header.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            System.out.println(1);
            return;
        }
        final String token = header.substring("Bearer ".length());
        System.out.println(3);
//        if(jwtService.validateToken(token)){
            Claims claims = jwtService.extractClaims(token);
            List<?> list = (List<?>) claims.get("roles", List.class);

            List<GrantedAuthority> list1 = list.stream().map(item -> {
                String role = (String) item;
                return new SimpleGrantedAuthority(role);
            }).collect(Collectors.toList());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(null, null, list1);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        }
        filterChain.doFilter(request, response);
    }
}
