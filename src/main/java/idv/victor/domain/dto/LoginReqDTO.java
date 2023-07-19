package idv.victor.domain.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 登入用的 login request
 */
@Data
@Builder
public class LoginReqDTO {
    /**
     * 帳號
     */
    private String userName;

    /**
     * 密碼
     */
    private String password;


}
