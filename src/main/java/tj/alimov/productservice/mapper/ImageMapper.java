package tj.alimov.productservice.mapper;

import tj.alimov.productservice.dto.img.ImageDto;
import tj.alimov.productservice.model.BaseImage;

import java.util.List;
import java.util.stream.Collectors;

public class ImageMapper {

    public static ImageDto toImageDto(BaseImage baseImage){
        return new ImageDto(baseImage.getSlug(), baseImage.getBaseUrl(), baseImage.getThumbnailUrl(), baseImage.getPosition());
    }

    public static List<ImageDto> toImageDtoList(List<BaseImage> list){
        return list.stream().map(ImageMapper::toImageDto).collect(Collectors.toList());
    }
}
