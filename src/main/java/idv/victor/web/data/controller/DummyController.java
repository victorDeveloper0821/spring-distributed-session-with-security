package idv.victor.web.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/dummy")
public class DummyController {
    @RequestMapping(value = "page1",method = {RequestMethod.GET,RequestMethod.POST})
    public String page1 (){
        return "dummyPage" ;
    }
}
