package tj.alimov.productservice.mapper.size;

import org.springframework.data.domain.Page;
import tj.alimov.productservice.dto.sizeOption.SizeOptionCreationRequest;
import tj.alimov.productservice.dto.sizeOption.SizeOptionDto;
import tj.alimov.productservice.model.size.SizeOption;

public class SizeOptionMapper {
    public static SizeOption toSizeOption(SizeOptionCreationRequest request){
        return SizeOption.builder()
                .ordering(request.order())
                .region(request.region())
                .value(request.value())
                .build();
    }

    public static SizeOptionDto toDto(SizeOption sizeOption){
        return new SizeOptionDto(sizeOption.getSlug(),
                                 sizeOption.getOrdering(),
                                 sizeOption.getSizeType().getName(),
                                 sizeOption.getSizeType().getDescription(),
                                 sizeOption.getValue(),
                                 sizeOption.getFitType().getName(),
                                 sizeOption.getRegion());
    }

    public static Page<SizeOptionDto> toDtoPage(Page<SizeOption> page){
        return page.map(SizeOptionMapper::toDto);
    }
}
