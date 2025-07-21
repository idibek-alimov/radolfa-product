package tj.alimov.productservice.mapper.brand;

import tj.alimov.productservice.dto.brand.BrandDto;
import tj.alimov.productservice.dto.brand.BrandCreationRequest;
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

        return new BrandDto(brand.getName(), brand.getSlug(), brand.getDescription(), brand.getUrl(), null);
    }

    public static List<BrandDto> toBrandDtoList(List<Brand> list){
        return list.stream().map(BrandMapper::toBrandDto).collect(Collectors.toList());
    }
}