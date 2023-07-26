package idv.victor.web.auth.controller;

import idv.victor.web.auth.domain.dto.FirstLoginReqDTO;
import idv.victor.web.auth.domain.dto.LoginReqDTO;
import idv.victor.web.auth.domain.dto.LoginResDTO;
import idv.victor.web.auth.service.AuthService;
import idv.victor.web.common.controller.domain.CommonResponse;
import idv.victor.web.enums.ErrorCodeEnum;
import idv.victor.web.exception.BussinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class AuthController {

    /**
     * 會員登入服務
     */
    @Autowired
    private AuthService authService;

    /**
     * 會員登入端點
     * @param loginReqDTO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public LoginResDTO loginPage(@RequestBody LoginReqDTO loginReqDTO) throws Exception {
        return authService.login(loginReqDTO) ;
    }

    /**
     * 會員 logout 端點
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public CommonResponse logout(HttpServletRequest request) throws BussinessException {
        authService.logout(request);
        return new CommonResponse(null, true, ErrorCodeEnum.A0001.getErrorCode());
    }

    /**
     * 會員註冊 api
     */
    @RequestMapping(value = "/firstLogin", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse firstLogin(@RequestBody FirstLoginReqDTO firstLoginReqDTO) throws Exception {
        authService.firstLogin(firstLoginReqDTO);
        return new CommonResponse(null, true, ErrorCodeEnum.A0001.getErrorCode());
    }
}
