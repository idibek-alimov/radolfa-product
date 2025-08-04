package tj.alimov.productservice.mapper.size;

import org.springframework.data.domain.Page;
import tj.alimov.productservice.dto.sizeType.SizeTypeCreationRequest;
import tj.alimov.productservice.dto.sizeType.SizeTypeDto;
import tj.alimov.productservice.model.size.SizeType;

public class SizeTypeMapper {
    public static SizeType toSizeType(SizeTypeCreationRequest request){
        return SizeType.builder()
                .name(request.name())
                .description(request.description())
                .build();
    }
    public static SizeTypeDto toDto(SizeType sizeType){
        return new SizeTypeDto(sizeType.getSlug(), sizeType.getName(), sizeType.getDescription());
    }

    public static Page<SizeTypeDto> toPageDto(Page<SizeType> page){
        return page.map(SizeTypeMapper::toDto);
    }
}
