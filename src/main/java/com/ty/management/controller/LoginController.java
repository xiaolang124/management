package com.ty.management.controller;

import com.ty.management.service.UserService;
import com.ty.management.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class
LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password){
        String result = JsonUtil.getJSONString(1,userService.register(username,password));
        return result;

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(){
        return "hello";
    }

}
