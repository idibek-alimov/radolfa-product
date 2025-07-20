package tj.alimov.productservice.mapper;

import tj.alimov.productservice.dto.brand.BrandDto;
import tj.alimov.productservice.dto.brand.BrandCreationRequest;
import tj.alimov.productservice.model.Brand;

import java.util.List;
import java.util.stream.Collectors;

public class BrandMapper {

    public static Brand toBrand(BrandCreationRequest request){
        return Brand.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public static BrandDto toBrandDto(Brand brand){
        return BrandDto.builder()
                .name(brand.getName())
                .description(brand.getDescription())
                .imgUrl(brand.getImgUrl())
                .build();
    }

    public static List<BrandDto> toBrandDtoList(List<Brand> list){
        return list.stream().map(BrandMapper::toBrandDto).collect(Collectors.toList());
    }
}