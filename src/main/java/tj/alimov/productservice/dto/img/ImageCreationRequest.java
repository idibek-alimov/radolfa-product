package tj.alimov.productservice.dto.img;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

public record ImageCreationRequest(@RequestPart MultipartFile image,@RequestPart Integer position) {}
