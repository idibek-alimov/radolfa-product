package tj.alimov.productservice.service.productArticle;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tj.alimov.productservice.dto.img.ImageDto;
import tj.alimov.productservice.dto.productArticle.*;
import tj.alimov.productservice.dto.productPriceHistory.ProductPriceHistoryCreationRequest;
import tj.alimov.productservice.exception.productArticle.ProductArticleIllegalAccessException;
import tj.alimov.productservice.exception.productArticle.ProductArticleNotFoundException;
import tj.alimov.productservice.mapper.productArticle.ProductArticleMapper;
import tj.alimov.productservice.model.color.ColorOption;
import tj.alimov.productservice.model.product.Product;
import tj.alimov.productservice.model.productArticle.ProductArticle;
import tj.alimov.productservice.model.productArticle.ProductPriceHistory;
import tj.alimov.productservice.model.productArticle.ProductVariant;
import tj.alimov.productservice.repository.productArticle.ProductArticleRepository;
import tj.alimov.productservice.service.color.ColorOptionService;
import tj.alimov.productservice.service.product.ProductService;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductArticleService {
    private final ProductArticleRepository productArticleRepository;
    private final ProductArticlePriceService productArticlePriceService;
    private final ColorOptionService colorService;
    private final ProductService productService;
    private final ProductArticleImageService productArticleImageService;
    private final ProductVariantService productVariantService;
    private final EntityManager entitymanager;
    @Transactional
    public ProductArticleDto createProductArticle(ProductArticleCreationRequest request, Long sellerId){
        Product product = productService.findProduct(request.productSlug());
        validateProductArticleCreation(request, product, sellerId);
        ColorOption color = colorService.findColor(request.colorSlug());
        ProductArticle productArticle = ProductArticle.builder()
                .product(product)
                .colorOption(color)
                .sellerId(sellerId)
                .slug(product.getSlug() + "-" + color.getSlug())
                .build();


        productArticle.setProductBaseIdentifier((getNewProductArticleIdentifier()));
        productArticleRepository.saveAndFlush(productArticle);
        List<ProductVariant> productVariants = productVariantService.createProductVariants(request.sizeSlugs(), productArticle);
        ProductPriceHistory price = productArticlePriceService.createPriceHistory(new ProductPriceHistoryCreationRequest(productArticle, request.price(), request.discount()));
        productArticle.addProductPriceHistory(price);
        System.out.println("Product Article identifier  = " + productArticle.getProductBaseIdentifier());
        return ProductArticleMapper.toDto(productArticle);
    }


    public ProductArticleDto getProductArticle(String slug){
        ProductArticleRawDto raw = productArticleRepository.findProductArticleDtoBySlug(slug).orElseThrow(()-> new ProductArticleNotFoundException("Product Article with given slug not found"));
        List<ImageDto> images = productArticleImageService.getProductArticleImages(slug);
        return ProductArticleMapper.rawToDto(raw, images);
    }

    @Transactional
    public ProductArticleDto updateProductArticle(ProductArticleUpdateRequest request, Long sellerId){
        ProductArticle productArticle = findProductArticleBySlug(request.slug());
        validateProductArticleUpdate(productArticle, sellerId);
        ColorOption color = colorService.findColor(request.colorSlug());
        ProductPriceHistory price = productArticlePriceService.createPriceHistory(new ProductPriceHistoryCreationRequest(productArticle, request.price(), request.discount()));

        productArticle.setColorOption(color);
        productArticle.addProductPriceHistory(price);
        productArticleRepository.save(productArticle);

        productVariantService.updateProductVariants(request.sizeSlugs(), productArticle);
        return ProductArticleMapper.toDto(productArticle);
    }

    public Page<ProductArticleListDto> getAll(Pageable pageable){
        Page<ProductArticleRawListDto> page = productArticleRepository.findAllListDTOs(pageable);
        List<ProductArticleListDto> enrichedContent = page.getContent().stream()
                .map(raw -> ProductArticleMapper.listRawToListDto(raw, productArticleImageService.getProductArticleImages(raw.slug()))
                ).toList();
        return new PageImpl<>(enrichedContent, pageable, page.getTotalElements());
    }
    private BigInteger getNewProductArticleIdentifier(){
        try{
            Object result =  entitymanager.createNativeQuery("SELECT nextval('seq_product_article_identifier_generator')").getSingleResult();
            BigInteger identifier;
            if(result instanceof Long) {
                identifier = BigInteger.valueOf((Long) result);
            }else{
                throw new IllegalStateException("Unexpected sequence value type: " + result.getClass());
            }
            return identifier;
        }catch(Exception e){
            throw new RuntimeException("Failed to fetch sequence value for productArticleIdentifier");
        }
    }
    public ProductArticle findProductArticleBySlug(String slug){
        return productArticleRepository.findBySlug(slug).orElseThrow(()-> new ProductArticleNotFoundException("Product Article with given slug not found"));
    }

    private void validateProductArticleCreation(ProductArticleCreationRequest request, Product product, Long sellerId){
        if(sellerId.equals(null)){
            throw new ProductArticleIllegalAccessException("SellerId not provided for creating product article");
        }
    }
    private void validateProductArticleUpdate(ProductArticle article, Long sellerId){
        if(sellerId == null){
            throw new IllegalArgumentException("Seller id can not be null");
        }
        if(!article.getSellerId().equals(sellerId)){
            throw new ProductArticleIllegalAccessException("Only owner of the product article can update it");
        }
    }

    private String generateUniqueSlug(String name){
        String baseSlug = name.trim().toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
        String slug = baseSlug;
        int suffix = 1;

        while(productArticleRepository.existsBySlug(slug)){
            slug = baseSlug + "-" + (suffix++);
        }
        return slug;
    }
}
