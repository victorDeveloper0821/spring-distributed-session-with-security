package idv.victor.web.auth.controller;

import idv.victor.web.auth.domain.dto.LoginReqDTO;
import idv.victor.web.auth.domain.dto.LoginResDTO;
import idv.victor.web.auth.service.AuthService;
import idv.victor.web.auth.service.impl.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
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
     * @return
     */
    @RequestMapping(value="/logout",method = RequestMethod.POST)
    public String logout(){
        return "" ;
    }
}
