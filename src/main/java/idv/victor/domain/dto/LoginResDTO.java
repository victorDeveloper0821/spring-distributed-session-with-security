package idv.victor.domain.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Login Response DTO
 */
@Data
@Builder
public class LoginResDTO {
    /**
     * JWT token
     */
    private String jwtToken ;
}
