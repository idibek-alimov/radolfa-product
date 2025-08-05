package tj.alimov.productservice.mapper.color;

import tj.alimov.productservice.dto.color.ColorCreationRequest;
import tj.alimov.productservice.dto.color.ColorDto;
import tj.alimov.productservice.model.color.ColorOption;

public class ColorOptionMapper {
    public static ColorOption toColorOption(ColorCreationRequest request){
        return ColorOption.builder()
                .name(request.name())
                .hexCode(request.hexCode())
                .build();
    }

    public static ColorDto toDto(ColorOption colorOption){
        return new ColorDto(colorOption.getSlug(), colorOption.getName(), colorOption.getHexCode());
    }
}
