package tj.alimov.productservice.mapper;

import tj.alimov.productservice.dto.brand.BrandDto;
import tj.alimov.productservice.dto.brand.BrandCreationRequest;
import tj.alimov.productservice.model.brand.Brand;


import java.util.List;
import java.util.stream.Collectors;

public class BrandMapper {

    public static Brand toBrand(BrandCreationRequest request){
        return Brand.builder()
                .name(request.name())
                .description(request.description())
                .build();
    }

    public static BrandDto toBrandDto(Brand brand){
        return new BrandDto(brand.getName(), brand.getDescription(), brand.getImages());
    }

    public static List<BrandDto> toBrandDtoList(List<Brand> list){
        return list.stream().map(BrandMapper::toBrandDto).collect(Collectors.toList());
    }
}