package tj.alimov.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tj.alimov.productservice.dto.fitType.FitTypeCreationRequest;
import tj.alimov.productservice.dto.fitType.FitTypeDto;
import tj.alimov.productservice.dto.fitType.FitTypeUpdateRequest;
import tj.alimov.productservice.dto.sizeOption.SizeOptionCreationRequest;
import tj.alimov.productservice.dto.sizeOption.SizeOptionDto;
import tj.alimov.productservice.dto.sizeOption.SizeOptionUpdateRequest;
import tj.alimov.productservice.dto.sizeType.SizeTypeCreationRequest;
import tj.alimov.productservice.dto.sizeType.SizeTypeDto;
import tj.alimov.productservice.dto.sizeType.SizeTypeUpdateRequest;
import tj.alimov.productservice.service.size.SizeService;

@RestController
@RequestMapping("/size")
@RequiredArgsConstructor
public class SizeController {
    private final SizeService sizeService;


    /** Size Option */
    @PostMapping("")
    public ResponseEntity<SizeOptionDto> createSize(@RequestBody SizeOptionCreationRequest request){
        SizeOptionDto dto = sizeService.createSize(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<SizeOptionDto> getSize(@PathVariable("slug") String slug){
        SizeOptionDto dto = sizeService.getSize(slug);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/by-fit-type/{slug}/{page}/{size}")
    public ResponseEntity<Page<SizeOptionDto>> findByFitType(@PathVariable("slug") String slug, @PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Page<SizeOptionDto> pageDTOs = sizeService.getSizeByFitType(slug, PageRequest.of(page, size));
        return ResponseEntity.ok(pageDTOs);
    }

    @GetMapping("/by-size-type/{slug}/{page}/{size}")
    public ResponseEntity<Page<SizeOptionDto>> findBySizeType(@PathVariable("slug") String slug, @PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Page<SizeOptionDto> pageDTOs = sizeService.getSizeBySizeType(slug, PageRequest.of(page, size));
        return ResponseEntity.ok(pageDTOs);
    }
    @GetMapping("/{page}/{size}")
    public ResponseEntity<Page<SizeOptionDto>> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Page<SizeOptionDto> pageDTOs = sizeService.findAll(PageRequest.of(page, size));
        return ResponseEntity.ok(pageDTOs);
    }

    @PutMapping("")
    public ResponseEntity<SizeOptionDto> updateSize(@RequestBody SizeOptionUpdateRequest request){
        SizeOptionDto dto = sizeService.updateSize(request);
        return ResponseEntity.ok(dto);
    }

    /** Fit Type */
    @PostMapping("/fit-type")
    public ResponseEntity<FitTypeDto> createFitType(@RequestBody FitTypeCreationRequest request){
        FitTypeDto dto = sizeService.createFitType(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/fit-type/{slug}")
    public ResponseEntity<FitTypeDto> getFitType(@PathVariable("slug") String slug){
        FitTypeDto dto = sizeService.getFitType(slug);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/fit-type")
    public ResponseEntity<FitTypeDto> updateFitType(@RequestBody FitTypeUpdateRequest request){
        FitTypeDto dto = sizeService.updateFitType(request);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/fit-type/all/{page}/{size}")
    public ResponseEntity<Page<FitTypeDto>> getAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Page<FitTypeDto> pageDTOs = sizeService.getAllFitTypes(PageRequest.of(page, size));
        return ResponseEntity.ok(pageDTOs);
    }

    /** Size Type */
    @PostMapping("/size-type")
    public ResponseEntity<SizeTypeDto> createSizeType(@RequestBody SizeTypeCreationRequest request){
        SizeTypeDto dto = sizeService.createSizeType(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/size-type/{slug}")
    public ResponseEntity<SizeTypeDto> getSizeType(@PathVariable("slug") String slug){
        SizeTypeDto dto = sizeService.getSizeType(slug);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/size-type/all/{page}/{size}")
    public ResponseEntity<Page<SizeTypeDto>> getAllSizeTypes(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Page<SizeTypeDto> pageDTOs = sizeService.getAllSizeTypes(PageRequest.of(page, size));
        return ResponseEntity.ok(pageDTOs);
    }

    @PutMapping("size-type")
    public ResponseEntity<SizeTypeDto> updateSizeType(@RequestBody SizeTypeUpdateRequest request){
        SizeTypeDto dto = sizeService.updateSizeType(request);
        return ResponseEntity.ok(dto);
    }

}
