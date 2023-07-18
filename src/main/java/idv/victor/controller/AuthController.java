package idv.victor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String loginPage(@RequestParam(value = "error",required = false)String error){
        return "loginPage" ;
    }

    @RequestMapping(value="/logout",method = RequestMethod.POST)
    public String logout(){
        return "" ;
    }
}
