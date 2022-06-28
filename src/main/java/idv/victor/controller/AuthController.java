package idv.victor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String loginPage(){
        return "" ;
    }

    @RequestMapping(value="/logout",method = RequestMethod.POST)
    public String logout(){
        return "" ;
    }
}
