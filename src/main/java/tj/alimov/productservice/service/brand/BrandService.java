package tj.alimov.productservice.service.brand;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tj.alimov.productservice.dto.brand.BrandCreationRequest;
import tj.alimov.productservice.dto.brand.BrandCreationResponse;
import tj.alimov.productservice.exception.brand.BrandDoesNotExistException;
import tj.alimov.productservice.exception.brand.BrandExistsException;
import tj.alimov.productservice.module.Brand;
import tj.alimov.productservice.repository.BrandRepository;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    @Transactional
    public BrandCreationResponse createBrand(BrandCreationRequest request){
        validateBrandCreationRequest(request);
        Brand brand = convertToBrand(request);
        brandRepository.save(brand);
        return convertToBrandCreationResponse(brand);
    }

    private BrandCreationResponse convertToBrandCreationResponse(Brand brand){
        return BrandCreationResponse.builder()
                .name(brand.getName())
                .imgUrl(brand.getImgUrl())
                .build();
    }
    private Brand convertToBrand(BrandCreationRequest request){
        return Brand.builder()
                .name(request.getName())
                .build();
    }

    private Brand validateAndGetParentBrand(Long id){
        if(id != null){
            return brandRepository.findById(id).orElseThrow(() -> new BrandDoesNotExistException("Brand with given id does not exist"));
        }
        return null;
    }
    private void validateBrandCreationRequest(BrandCreationRequest request){
        if(brandRepository.existsByName(request.getName())){
            throw new BrandExistsException("Brand with given name already exists");
        }
    }
}
