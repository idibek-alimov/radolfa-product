package tj.alimov.productservice.dto.img;

import org.springframework.web.multipart.MultipartFile;

public record ImageCreationRequest(MultipartFile image, Integer position) {}
