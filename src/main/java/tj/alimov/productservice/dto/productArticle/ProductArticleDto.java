package tj.alimov.productservice.dto.productArticle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tj.alimov.productservice.dto.img.ImageDto;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductArticleDto{
    private String slug;
    private String productBaseIdentifier;
    private String currentPrice;
    private String discountPercentage;
    private String discountPrice;
    private String name;
    private List<ImageDto> images;
}
