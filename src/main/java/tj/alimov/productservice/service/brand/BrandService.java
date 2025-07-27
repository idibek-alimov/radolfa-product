package tj.alimov.productservice.service.brand;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tj.alimov.productservice.dto.brand.BrandCreationRequest;
import tj.alimov.productservice.dto.brand.BrandDto;
import tj.alimov.productservice.dto.brand.BrandUpdateRequest;
import tj.alimov.productservice.dto.img.ImageCreationRequest;
import tj.alimov.productservice.exception.brand.BrandExistsException;


import tj.alimov.productservice.exception.brand.BrandNotFoundException;
import tj.alimov.productservice.mapper.brand.BrandMapper;
import tj.alimov.productservice.model.brand.Brand;
import tj.alimov.productservice.model.brand.BrandImage;
import tj.alimov.productservice.repository.brand.BrandImageRepository;
import tj.alimov.productservice.repository.brand.BrandRepository;
import tj.alimov.productservice.storage.StorageService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final BrandImageRepository brandImageRepository;
    private final StorageService storageService;

    /** Creation a brand */
    @Transactional
    public BrandDto createBrand(BrandCreationRequest request){
        validateBrandCreation(request);
        Brand brand = BrandMapper.toBrand(request);
        brand.setSlug(generateUniqueSlug(brand.getName()));
        brandRepository.save(brand);
        System.out.println(String.format("brand.getImages() = %s", brand.getImages()));
        return BrandMapper.toBrandDto(brand);
    }

    /** Get Brand By slug */
    public BrandDto getBrandBySlug(String slug){
        Brand brand = findBrandBySlug(slug);
        return BrandMapper.toBrandDto(brand);
    }

    /** Update Brand */
    @Transactional
    public BrandDto updateBrand(BrandUpdateRequest request){
        validateBrandUpdate(request);
        Brand brand = findBrandBySlug(request.slug());
        brand.setName(request.name());
        brand.setDescription(request.description());
        brand.setUrl(request.url());
        brandRepository.save(brand);
        return BrandMapper.toBrandDto(brand);
    }
    /** Get all brands */
    public Page<BrandDto> getAll(Pageable pageable){
        Page<Brand> brands = brandRepository.findAll(pageable);
        return BrandMapper.toBrandDtoPage(brands);
    }

    /** Add Image */
    @Transactional
    public BrandDto addImages(String slug, List<ImageCreationRequest> images){
        Brand brand = findBrandBySlug(slug);
        List<BrandImage> brandImages = new ArrayList<>();
        for(ImageCreationRequest item : images){
            BrandImage brandImage = storageService.saveBrandImage(item);
            brandImage.setBrand(brand);
            brandImage.setSlug(generateUniqueImageSlug(brand.getName()));
            brandImages.add(brandImage);
        }
        brandImageRepository.saveAll(brandImages);

        return BrandMapper.toBrandDto(brand);
    }

    @Transactional
    public void deleteImages(List<String> slugs){
        List<BrandImage> images = brandImageRepository.findBySlugCaseInsensitive(slugs);
        for(BrandImage image : images){
            image.setActive(false);
        }
        brandImageRepository.saveAll(images);
    }


    /** Find brand by slug */
    public Brand findBrandBySlug(String slug){
        return brandRepository.findBySlug(slug).orElseThrow(()-> new BrandNotFoundException("Brand with given slug does not exist", 404));
    }

    /** Brand creation validation */
    private void validateBrandCreation(BrandCreationRequest request){
        if(brandRepository.existsByName(request.name())){
            throw new BrandExistsException("Brand with given name already exists", 409);
        }
    }

    /** Brand update validation */
    private void validateBrandUpdate(BrandUpdateRequest request){
        if(brandRepository.existsByName(request.name())){
            throw new BrandExistsException("Brand with given name already exists", 409);
        }
    }

    /** Generate Brand Slug */
    private String generateUniqueSlug(String name){
        String baseSlug = name.trim().toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
        String slug = baseSlug;
        int suffix = 1;

        while(brandRepository.existsBySlug(slug)){
            slug = baseSlug + "-" + (suffix++);
        }
        return slug;
    }

    private String generateUniqueImageSlug(String name){
        String baseSlug = name.trim().toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
        String slug = baseSlug;
        int suffix = 1;

        while(brandImageRepository.existsBySlug(slug)){
            slug = baseSlug + "-" + (suffix++);
        }
        return slug;
    }
}
