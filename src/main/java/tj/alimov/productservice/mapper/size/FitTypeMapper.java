package tj.alimov.productservice.mapper.size;

import org.springframework.data.domain.Page;
import tj.alimov.productservice.dto.fitType.FitTypeCreationRequest;
import tj.alimov.productservice.dto.fitType.FitTypeDto;
import tj.alimov.productservice.model.size.FitType;

public class FitTypeMapper {
    public static FitType toFitType(FitTypeCreationRequest request){
        return FitType.builder()
                .name(request.name())
                .build();
    }

    public static FitTypeDto toDto(FitType fitType){
        return new FitTypeDto(fitType.getSlug(), fitType.getName());
    }
    public static Page<FitTypeDto> toPageDto(Page<FitType> page){
        return page.map(FitTypeMapper::toDto);
    }
}
