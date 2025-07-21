package tj.alimov.productservice.service.brand;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tj.alimov.productservice.dto.brand.BrandCreationRequest;
import tj.alimov.productservice.dto.brand.BrandDto;
import tj.alimov.productservice.exception.brand.BrandExistsException;
import tj.alimov.productservice.repository.BrandRepository;
import tj.alimov.productservice.storage.StorageService;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final StorageService storageService;

    @Transactional
    public BrandDto createBrand(BrandCreationRequest request){
        validateBrandCreation(request);
        return null;
    }

    private void validateBrandCreation(BrandCreationRequest request){
        if(brandRepository.existsByName(request.getName())){
            throw new BrandExistsException("Brand with given name already exists");
        }
    }
//    @Transactional
//    public BrandDto createBrand(BrandCreationRequest request){
//        validateBrandCreationRequest(request);
//        Brand brand = BrandMapper.toBrand(request);
//        brandRepository.save(brand);
//        return BrandMapper.toBrandDto(brand);
//    }
//
//    public BrandDto updateImg(BrandImgUpdateRequest request){
//        Brand brand = brandRepository.findById(request.getId()).orElseThrow(() -> new BrandDoesNotExistException("Brand with given id does not exist"));
//        String url = storageService.saveFile(request.getImg());
//        brand.setImgUrl(url);
//        return BrandMapper.toBrandDto(brand);
//    }
//    public BrandDto getBrandDto(Long id){
//        Brand brand = brandRepository.findById(id).orElseThrow(() -> new BrandDoesNotExistException("Brand does not exist exception"));
//        return BrandMapper.toBrandDto(brand);
//    }
//    public Brand getBrand(Long id){
//        return brandRepository.findById(id).orElseThrow(() -> new BrandDoesNotExistException("Brand does not exist exception"));
//    }
//    public List<BrandDto> getBrands(){
//        List<Brand> brands = brandRepository.findAll();
//        return BrandMapper.toBrandDtoList(brands);
//    }
//    public boolean existById(Long id){
//        return brandRepository.existsById(id);
//        ///
//    }
//    private Brand validateAndGetParentBrand(Long id){
//        if(id != null){
//            return brandRepository.findById(id).orElseThrow(() -> new BrandDoesNotExistException("Brand with given id does not exist"));
//        }
//        return null;
//    }
//    private void validateBrandCreationRequest(BrandCreationRequest request){
//        if(brandRepository.existsByName(request.getName())){
//            throw new BrandExistsException("Brand with given name already exists");
//        }
//    }
}
