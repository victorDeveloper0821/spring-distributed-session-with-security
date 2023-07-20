package idv.victor.web.auth.service;

import idv.victor.web.auth.domain.dto.LoginReqDTO;
import idv.victor.web.auth.domain.dto.LoginResDTO;

public interface AuthService {

    /**
     * 會員登入邏輯
     * @param loginReqDTO
     * @return
     * @throws Exception
     */
    public LoginResDTO login(LoginReqDTO loginReqDTO) throws Exception;

    /**
     * 會員登出
     */
    public void logout();
}
