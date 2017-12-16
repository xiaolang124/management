package com.ty.management.controller;

import com.ty.management.service.UserService;
import com.ty.management.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.sun.corba.se.spi.activation.IIOP_CLEAR_TEXT.value;


@Controller
public class
LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestParam("username") String username,
                        @RequestParam("password") String password){
        String result = JsonUtil.getJSONString(1,userService.register(username,password));
        return result;
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam(value = "remember",defaultValue = "1") int remember,
                        HttpServletResponse response){
        try{
            Map<String, Object> map = userService.login(username, password);
            if(map.containsKey("ticket")){
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                if(remember==1){
                    cookie.setMaxAge(3600*24);
                }
                response.addCookie(cookie);
                return JsonUtil.getJSONString(0,"成功");
            }else{
                return JsonUtil.getJSONString(1,map);
            }
        }catch (Exception e){
            return JsonUtil.getJSONString(1,e.toString());
        }
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(@RequestParam("password") String password){
        String result = JsonUtil.getJSONString(1,userService.updateUserPassword(password));
        return result;
    }


}
