package tj.alimov.productservice.mapper.product;



import org.springframework.data.domain.Page;
import tj.alimov.productservice.dto.productAttributeTemplate.ProductAttributeTemplateCreationRequest;
import tj.alimov.productservice.dto.productAttributeTemplate.ProductAttributeTemplateDto;
import tj.alimov.productservice.model.AttributeDataType;


import java.util.List;
import java.util.stream.Collectors;

public class ProductAttributeTemplateMapper {

    public static ProductAttributeTemplate toProductAttributeTemplate(ProductAttributeTemplateCreationRequest request, ProductType productType){
        return ProductAttributeTemplate.builder()
                .name(request.name())
                .required(request.required())
                .type(AttributeDataType.getByName(request.type()))
                .productType(productType)
                .build();
    }

    public static ProductAttributeTemplateDto toDto(ProductAttributeTemplate template){
        return new ProductAttributeTemplateDto(template.getSlug(), template.getName(), template.isRequired(), template.getType().getName());
    }

    public static List<ProductAttributeTemplateDto> toDtoList(List<ProductAttributeTemplate> list){
        return list.stream().map(ProductAttributeTemplateMapper::toDto).collect(Collectors.toList());
    }

    public static Page<ProductAttributeTemplateDto> toDtoPage(Page<ProductAttributeTemplate> page){
        return page.map(ProductAttributeTemplateMapper::toDto);
    }
}
