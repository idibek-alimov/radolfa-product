package tj.alimov.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tj.alimov.productservice.dto.color.ColorCreationRequest;
import tj.alimov.productservice.dto.color.ColorDto;
import tj.alimov.productservice.dto.color.ColorUpdateRequest;
import tj.alimov.productservice.service.color.ColorOptionService;

@RestController
@RequestMapping("/color")
@RequiredArgsConstructor
public class ColorController {
    private final ColorOptionService colorOptionService;

    @PostMapping("")
    public ResponseEntity<ColorDto> createColor(@RequestBody ColorCreationRequest colorCreationRequest){
        ColorDto dto = colorOptionService.createColor(colorCreationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("{slug}")
    public ResponseEntity<ColorDto> getColor(@PathVariable("slug") String slug){
        ColorDto dto = colorOptionService.getColorOption(slug);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("")
    public ResponseEntity<ColorDto> updateColor(@RequestBody ColorUpdateRequest request){
        ColorDto dto = colorOptionService.updateColorOption(request);
        return ResponseEntity.ok(dto);
    }
}
