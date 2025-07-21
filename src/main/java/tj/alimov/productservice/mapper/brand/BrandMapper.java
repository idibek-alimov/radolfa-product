package tj.alimov.productservice.mapper.brand;

import org.springframework.data.domain.Page;
import tj.alimov.productservice.dto.brand.BrandDto;
import tj.alimov.productservice.dto.brand.BrandCreationRequest;
import tj.alimov.productservice.dto.img.ImageDto;
import tj.alimov.productservice.mapper.ImageMapper;
import tj.alimov.productservice.model.BaseImage;
import tj.alimov.productservice.model.brand.Brand;
import tj.alimov.productservice.model.brand.BrandImage;

import java.util.List;
import java.util.stream.Collectors;

public class BrandMapper {

    public static Brand toBrand(BrandCreationRequest request){
        return Brand.builder()
                .name(request.getName())
                .description(request.getDescription())
                .url(request.getUrl())
                .build();
    }

    public static BrandDto toBrandDto(Brand brand){
        List<BrandImage> brandImages = brand.getImages();
        List<ImageDto> images = brandImages.stream().filter(BaseImage::getActive).map(ImageMapper::toImageDto).collect(Collectors.toList());
        return new BrandDto(brand.getName(), brand.getSlug(), brand.getDescription(), brand.getUrl(), images);
    }

    public static List<BrandDto> toBrandDtoList(List<Brand> list){
        return list.stream().map(BrandMapper::toBrandDto).collect(Collectors.toList());
    }

    public static Page<BrandDto> toBrandDtoPage(Page<Brand> page){
        return page.map(BrandMapper::toBrandDto);
    }
}