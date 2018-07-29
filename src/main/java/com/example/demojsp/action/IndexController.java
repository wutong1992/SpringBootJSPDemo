package com.example.demojsp.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/user/login",method = RequestMethod.GET)
    public String login(String name, String pwd) {
        String result = "login";
        System.out.println("name : " + name);
        System.out.println("pwd : " + pwd);
        if(name.equals("root") && pwd.equals("root")) {
            result = "index";
        }
        return result;
    }
}
