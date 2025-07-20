package tj.alimov.productservice.dto.exception;

import lombok.Builder;
import lombok.Data;


public record ApiException (String message, Integer code){}
