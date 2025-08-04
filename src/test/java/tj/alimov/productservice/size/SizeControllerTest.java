package tj.alimov.productservice.size;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tj.alimov.productservice.controller.SizeController;
import tj.alimov.productservice.dto.fitType.FitTypeCreationRequest;
import tj.alimov.productservice.dto.fitType.FitTypeDto;
import tj.alimov.productservice.dto.fitType.FitTypeUpdateRequest;
import tj.alimov.productservice.filter.AuthenticationFilter;
import tj.alimov.productservice.repository.size.SizeOptionRepository;
import tj.alimov.productservice.service.JwtService;
import tj.alimov.productservice.service.size.SizeService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SizeController.class)
@AutoConfigureMockMvc(addFilters = false)
//@RequiredArgsConstructor
public class SizeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SizeService sizeService;
    @MockBean
    private JwtService jwtService;

    @MockBean
    private AuthenticationFilter authenticationFilter;

    /** FitType */
    @Test
    void createFitTypeTest() throws Exception{
        FitTypeCreationRequest request = new FitTypeCreationRequest("baby-fit");
        FitTypeDto response = new FitTypeDto("baby-fit", "baby-fit");

        when(sizeService.createFitType(any())).thenReturn(response);

        mockMvc.perform(post("/size/fit-type")
                .contentType(MediaType.APPLICATION_JSON)
                .content((new ObjectMapper().writeValueAsString(request))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.slug").value("baby-fit"))
                .andExpect(jsonPath("$.name").value("baby-fit"));
    }
    @Test
    void getFitTypeTest() throws Exception{
        String slug = "baby-fit";
        FitTypeDto response = new FitTypeDto("baby-fit", "baby-fit");

        when(sizeService.getFitType(slug)).thenReturn(response);

        mockMvc.perform(get("/size/fit-type/{slug}", slug)
                .contentType(MediaType.APPLICATION_JSON))
//                .content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.slug").value("baby-fit"))
                .andExpect(jsonPath("$.name").value("baby-fit"));
    }

    @Test
    void updateFitTypeTest() throws Exception{
        FitTypeUpdateRequest request = new FitTypeUpdateRequest("baby-fit", "baby-fit");
        FitTypeDto response = new FitTypeDto("baby-fit", "baby-fit-updated");

        when(sizeService.updateFitType(request)).thenReturn(response);

        mockMvc.perform(put("/size/fit-type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.slug").value("baby-fit"))
                .andExpect(jsonPath("$.name").value("baby-fit-updated"));
    }

}
