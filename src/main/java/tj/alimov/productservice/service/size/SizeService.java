package tj.alimov.productservice.service.size;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tj.alimov.productservice.dto.fitType.FitTypeCreationRequest;
import tj.alimov.productservice.dto.fitType.FitTypeDto;
import tj.alimov.productservice.dto.fitType.FitTypeUpdateRequest;
import tj.alimov.productservice.dto.sizeOption.SizeOptionCreationRequest;
import tj.alimov.productservice.dto.sizeOption.SizeOptionDto;
import tj.alimov.productservice.dto.sizeOption.SizeOptionUpdateRequest;
import tj.alimov.productservice.dto.sizeType.SizeTypeCreationRequest;
import tj.alimov.productservice.dto.sizeType.SizeTypeDto;
import tj.alimov.productservice.dto.sizeType.SizeTypeUpdateRequest;
import tj.alimov.productservice.exception.size.*;
import tj.alimov.productservice.mapper.size.FitTypeMapper;
import tj.alimov.productservice.mapper.size.SizeOptionMapper;
import tj.alimov.productservice.mapper.size.SizeTypeMapper;
import tj.alimov.productservice.model.size.FitType;
import tj.alimov.productservice.model.size.SizeOption;
import tj.alimov.productservice.model.size.SizeType;
import tj.alimov.productservice.repository.size.FitTypeRepository;
import tj.alimov.productservice.repository.size.SizeOptionRepository;
import tj.alimov.productservice.repository.size.SizeTypeRepository;

@Service
@RequiredArgsConstructor
public class SizeService {
    private final SizeOptionRepository sizeOptionRepository;
    private final SizeTypeRepository sizeTypeRepository;
    private final FitTypeRepository fitTypeRepository;

    /** SIZE OPTION */
    @Transactional
    public SizeOptionDto createSize(SizeOptionCreationRequest request){
        validateSizeCreation(request);
        FitType fitType = findFitType(request.fitTypeSlug());
        SizeType sizeType = findSizeType(request.sizeTypeSlug());
        SizeOption sizeOption = SizeOptionMapper.toSizeOption(request);
        sizeOption.setSizeType(sizeType);
        sizeOption.setFitType(fitType);
        sizeOption.setSlug(generateUniqueSizeOptionSlug(fitType.getSlug()  + "-"+ sizeType.getSlug() + "-" + request.value()));
        sizeOptionRepository.save(sizeOption);
        return SizeOptionMapper.toDto(sizeOption);
    }
    public SizeOptionDto getSize(String slug){
        return sizeOptionRepository.getDtoBySlug(slug).orElseThrow(()-> new SizeOptionNotFoundException("SizeOption with given slug does not exist"));
    }

    public Page<SizeOptionDto> getSizeByFitType(String slug, Pageable pageable){
        return sizeOptionRepository.getDtoByFitTypeSlug(slug, pageable);
    }
    public Page<SizeOptionDto> getSizeBySizeType(String slug, Pageable pageable){
        return sizeOptionRepository.getDtoBySizeTypeSlug(slug, pageable);
    }
    public SizeOptionDto updateSize(SizeOptionUpdateRequest request){
        SizeOption sizeOption = findSize(request.slug());
        sizeOption.setOrdering(request.order());
        sizeOption.setRegion(request.region());
        sizeOption.setValue(request.value());

        FitType fitType = findFitType(request.fitTypeSlug());
        SizeType sizeType = findSizeType(request.sizeTypeSlug());
        sizeOption.setFitType(fitType);
        sizeOption.setSizeType(sizeType);
        sizeOptionRepository.save(sizeOption);
        return SizeOptionMapper.toDto(sizeOption);
    }
    public Page<SizeOptionDto> findAll(Pageable pageable){
        Page<SizeOption> page = sizeOptionRepository.findAll(pageable);
        return SizeOptionMapper.toDtoPage(page);
    }
    public SizeOption findSize(String slug){
        return sizeOptionRepository.findBySlug(slug).orElseThrow(()-> new SizeOptionNotFoundException("SizeOption with given slug does not exist"));
    }
    private void validateSizeCreation(SizeOptionCreationRequest request){
        if(sizeOptionRepository.sizeOptionExists(request)){
            throw new SizeOptionConflictException("SizeOption with given values already exists");
        }
    }
    private String generateUniqueSizeOptionSlug(String name){
        String baseSlug = name.trim().toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
        String slug = baseSlug;
        int suffix = 1;

        while(sizeOptionRepository.existsBySlug(slug)){
            slug = baseSlug + "-" + (suffix++);
        }
        return slug;
    }

    /** SIZE TYPE */
    @Transactional
    public SizeTypeDto createSizeType(SizeTypeCreationRequest request){
        validateSizeTypeCreation(request);
        SizeType sizeType = SizeTypeMapper.toSizeType(request);
        sizeType.setSlug(generateUniqueSizeTypeSlug(request.name()));
        sizeTypeRepository.save(sizeType);
        return SizeTypeMapper.toDto(sizeType);
    }
    public SizeTypeDto getSizeType(String slug){
        SizeType sizeType = findSizeType(slug);
        return SizeTypeMapper.toDto(sizeType);
    }
    public Page<SizeTypeDto> getAllSizeTypes(Pageable pageable){
        Page<SizeType> page = sizeTypeRepository.findAll(pageable);
        return SizeTypeMapper.toPageDto(page);
    }

    @Transactional
    public SizeTypeDto updateSizeType(SizeTypeUpdateRequest request){
        SizeType sizeType = findSizeType(request.slug());
        sizeType.setName(request.name());
        sizeType.setDescription(request.description());
        sizeTypeRepository.save(sizeType);
        return SizeTypeMapper.toDto(sizeType);
    }

    public SizeType findSizeType(String slug){
        return sizeTypeRepository.findBySlug(slug).orElseThrow(() -> new SizeTypeNotFoundException("SizeType with given slug does not exist"));
    }
    private void validateSizeTypeCreation(SizeTypeCreationRequest request){
        if(sizeTypeRepository.existsByName(request.name())){
            throw new SizeTypeConflictException("SizeType with given name already exists");
        }
    }
    private String generateUniqueSizeTypeSlug(String name){
        String baseSlug = name.trim().toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
        String slug = baseSlug;
        int suffix = 1;

        while(sizeTypeRepository.existsBySlug(slug)){
            slug = baseSlug + "-" + (suffix++);
        }
        return slug;
    }

    /** FIT TYPE */
    @Transactional
    public FitTypeDto createFitType(FitTypeCreationRequest request){
        validateFitTypeCreation(request);
        FitType fitType = FitTypeMapper.toFitType(request);
        fitType.setSlug(generateUniqueFitTypeSlug(request.name()));
        fitTypeRepository.save(fitType);
        return FitTypeMapper.toDto(fitType);
    }

    public FitTypeDto getFitType(String slug){
        FitType fitType = findFitType(slug);
        return FitTypeMapper.toDto(fitType);
    }

    public Page<FitTypeDto> getAllFitTypes(Pageable pageable){
        Page<FitType> page = fitTypeRepository.findAll(pageable);
        return FitTypeMapper.toPageDto(page);
    }

    @Transactional
    public FitTypeDto updateFitType(FitTypeUpdateRequest request){
        FitType fitType = findFitType(request.slug());
        fitType.setName(request.name());
        fitTypeRepository.save(fitType);
        return FitTypeMapper.toDto(fitType);
    }

    public FitType findFitType(String slug){
        return fitTypeRepository.findBySlug(slug).orElseThrow(() -> new FitTypeNotFoundException("Fit Type with given slug not found"));
    }
    private void validateFitTypeCreation(FitTypeCreationRequest request){
        if(fitTypeRepository.existsByName(request.name())){
            throw new FitTypeConflictException("FitType with given name already exists");
        }
    }
    private String generateUniqueFitTypeSlug(String name){
        String baseSlug = name.trim().toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
        String slug = baseSlug;
        int suffix = 1;

        while(fitTypeRepository.existsBySlug(slug)){
            slug = baseSlug + "-" + (suffix++);
        }
        return slug;
    }

}
