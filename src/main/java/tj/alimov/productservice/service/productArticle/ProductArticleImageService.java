package tj.alimov.productservice.service.productArticle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tj.alimov.productservice.dto.img.ImageDto;
import tj.alimov.productservice.mapper.ImageMapper;
import tj.alimov.productservice.model.BaseImage;
import tj.alimov.productservice.model.productArticle.ProductArticleImage;
import tj.alimov.productservice.repository.productArticle.ProductArticleImageRepository;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductArticleImageService {
    private final ProductArticleImageRepository repository;

    public List<ImageDto> getProductArticleImages(String slug){
        List<ProductArticleImage> images = repository.getByProductArticleSlug(slug);
        List<ImageDto> DTOs = images.stream().sorted(Comparator.comparingInt(BaseImage::getPosition)).map(ImageMapper::toImageDto).toList();
        return DTOs;
    }
}
