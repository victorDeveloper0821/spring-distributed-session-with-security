package idv.victor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @RequestMapping(value = "/index")
    public String indexPage(){
        return "home";
    }
}
