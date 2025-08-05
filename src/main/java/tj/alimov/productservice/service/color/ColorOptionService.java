package tj.alimov.productservice.service.color;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tj.alimov.productservice.dto.color.ColorCreationRequest;
import tj.alimov.productservice.dto.color.ColorDto;
import tj.alimov.productservice.dto.color.ColorUpdateRequest;
import tj.alimov.productservice.exception.color.ColorOptionConflictException;
import tj.alimov.productservice.exception.color.ColorOptionNotFoundException;
import tj.alimov.productservice.mapper.color.ColorOptionMapper;
import tj.alimov.productservice.model.color.ColorOption;
import tj.alimov.productservice.repository.ColorOptionRepository;

@Service
@RequiredArgsConstructor
public class ColorOptionService {
    private final ColorOptionRepository colorOptionRepository;

    @Transactional
    public ColorDto createColor(ColorCreationRequest request){
        validateColorCreation(request);
        ColorOption colorOption = ColorOptionMapper.toColorOption(request);
        colorOption.setSlug(generateUniqueSizeOptionSlug(request.name()));
        colorOptionRepository.save(colorOption);
        return ColorOptionMapper.toDto(colorOption);
    }
    public ColorDto getColorOption(String slug){
        ColorOption colorOption = findColor(slug);
        return ColorOptionMapper.toDto(colorOption);
    }

    @Transactional
    public ColorDto updateColorOption(ColorUpdateRequest request){
        ColorOption colorOption = findColor(request.slug());
        colorOption.setName(request.name());
        colorOption.setHexCode(request.hexCode());
        colorOptionRepository.save(colorOption);
        return ColorOptionMapper.toDto(colorOption);
    }

    public ColorOption findColor(String slug){
        return colorOptionRepository.findBySlug(slug).orElseThrow(()-> new ColorOptionNotFoundException("ColorOption with given name does not exist"));
    }
    private void validateColorCreation(ColorCreationRequest request){
        if(colorOptionRepository.existsByNameOrHexCode(request.name(), request.hexCode())){
            throw new ColorOptionConflictException("ColorOption with given name or hexCode already exists");
        }
    }

    private String generateUniqueSizeOptionSlug(String name){
        String baseSlug = name.trim().toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
        String slug = baseSlug;
        int suffix = 1;

        while(colorOptionRepository.existsBySlug(slug)){
            slug = baseSlug + "-" + (suffix++);
        }
        return slug;
    }

}
