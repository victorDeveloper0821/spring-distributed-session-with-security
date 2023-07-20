package idv.victor.web.auth.controller;

import idv.victor.web.auth.domain.dto.LoginReqDTO;
import idv.victor.web.auth.domain.dto.LoginResDTO;
import idv.victor.web.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public LoginResDTO loginPage(@RequestBody LoginReqDTO loginReqDTO){
        return authService.login(loginReqDTO) ;
    }

    @RequestMapping(value="/logout",method = RequestMethod.POST)
    public String logout(){
        return "" ;
    }
}
