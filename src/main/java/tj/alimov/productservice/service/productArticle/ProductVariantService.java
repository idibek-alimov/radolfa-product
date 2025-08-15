package tj.alimov.productservice.service.productArticle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tj.alimov.productservice.model.productArticle.ProductArticle;
import tj.alimov.productservice.model.productArticle.ProductVariant;
import tj.alimov.productservice.model.size.SizeOption;
import tj.alimov.productservice.repository.productArticle.ProductVariantRepository;
import tj.alimov.productservice.service.size.SizeService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductVariantService {
    private final ProductVariantRepository productVariantRepository;
    private final SizeService sizeService;

    @Transactional
    public List<ProductVariant> createProductVariants(List<String > sizeSlugs, ProductArticle productArticle){
        List<ProductVariant> productVariants = new ArrayList<>();

        for(String slug: sizeSlugs){
            SizeOption sizeOption = sizeService.findSize(slug);
            ProductVariant productVariant = ProductVariant.builder()
                    .sizeOption(sizeOption)
                    .skuCode(productArticle.getSlug() + sizeOption.getSlug())
                    .productArticle(productArticle)
                    .build();

            productVariants.add(productVariant);
        }
        productVariantRepository.saveAll(productVariants);
        return productVariants;
    }

    @Transactional
    public List<ProductVariant> updateProductVariants(List<String> sizeSlugs, ProductArticle productArticle){
        List<ProductVariant> existingVariants = productVariantRepository.findByProductArticle(productArticle);
        List<String> existingSizeSlugs = existingVariants.stream().map(v -> v.getSizeOption().getSlug()).toList();

        List<String> sizeToCreate = sizeSlugs.stream().filter(ss -> !existingSizeSlugs.contains(ss)).toList();

        List<ProductVariant> variantsToDeactivate = existingVariants.stream().filter(v -> !sizeSlugs.contains(v.getSizeOption().getSlug())).toList();

        // Deactivate
        for(ProductVariant productVariant : variantsToDeactivate){
            productVariant.setActive(false);
        }
        productVariantRepository.saveAll(variantsToDeactivate);
        List<ProductVariant> createdVariants = createProductVariants(sizeToCreate, productArticle);

        List<ProductVariant> results = new ArrayList<>(createdVariants);
        results.addAll(variantsToDeactivate);
        return results;
    }

    private String generateUniqueSkuCode(String name){
        String baseSkuCode = name.trim().toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
        String skuCode = baseSkuCode;
        int suffix = 1;

        while(productVariantRepository.existsBySkuCode(skuCode)){
            skuCode = baseSkuCode + "-" + (suffix++);
        }
        return skuCode;
    }

}
