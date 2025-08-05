package tj.alimov.productservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import tj.alimov.productservice.dto.product.response.ProductDto;
import tj.alimov.productservice.service.JwtService;
import tj.alimov.productservice.service.product.ProductService;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.anyLong;

