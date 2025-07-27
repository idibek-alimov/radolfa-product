package tj.alimov.productservice.mapper.product;

import tj.alimov.productservice.dto.product.response.ProductAttributeTemplateDto;
import tj.alimov.productservice.dto.product.request.ProductAttributeTemplateRequest;
import tj.alimov.productservice.model.product.ProductAttributeTemplate;
import tj.alimov.productservice.model.product.ProductType;

import java.util.List;
import java.util.stream.Collectors;

public class ProductAttributeTemplateMapper {
    public static ProductAttributeTemplate toProductAttributeTemplate(ProductAttributeTemplateRequest request, ProductType productType){
        return ProductAttributeTemplate.builder()
                .name(request.getName())
                .required(request.isRequired())
                .type(request.getType())
                .productType(productType)
                .build();
    }

    public static ProductAttributeTemplateDto toDto(ProductAttributeTemplate template){
        return ProductAttributeTemplateDto.builder()
                .name(template.getName())
                .required(template.isRequired())
                .build();
    }

    public static List<ProductAttributeTemplateDto> toDtoList(List<ProductAttributeTemplate> list){
        return list.stream().map(ProductAttributeTemplateMapper::toDto).collect(Collectors.toList());
    }
}
