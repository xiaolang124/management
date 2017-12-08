package com.ty.management.service;

import com.ty.management.dao.UserDAO;
import com.ty.management.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public Map<String, Object> register(String username, String password){
        Map<String,Object> map = new HashMap<>();
        if(StringUtils.isBlank(username)){
            map.put("msgUserName","用户名不能为空");
            return map;
        }

        if(StringUtils.isBlank(password)){
            map.put("msgPassword", "密码不能为空");
            return map;
        }

        User user = userDAO.selectByName(username);
        map.put("user", "123");
        return map;
    }
}
