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

public class ProductControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private ProductController productController;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

//    @Test
//    void getProduct_ShouldReturnProduct() throws Exception{
//        ProductDto mockProduct = new ProductDto("TestProduct", "Description");
//
//        when(productService.getProductDto(anyLong())).thenReturn(mockProduct);
//
////        mockMvc.perform(get("/products/1")).andExpect(status.ok)
//    }

}
