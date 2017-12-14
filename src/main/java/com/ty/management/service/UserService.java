package com.ty.management.service;

import com.ty.management.dao.UserDAO;
import com.ty.management.model.HostHolder;
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

    @Autowired
    private HostHolder hostHolder;

    public Map<String, Object> register(String username, String password){
        Map<String,Object> map = new HashMap<>();
        if(StringUtils.isBlank(username)){
            map.put("201","用户名不能为空");
            return map;
        }

        if(StringUtils.isBlank(password)){
            map.put("202", "密码不能为空");
            return map;
        }
        try{
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userDAO.addUser(user);
            map.put("000","成功");
            return map;
        }catch (Exception e){
            map.put("300",e.toString());
        }
        return map;
    }

    public  Map<String ,Object> login(String username,String password){
        Map<String,Object> map = new HashMap<>();
        if(StringUtils.isBlank(username)){
            map.put("201","用户名不能为空");
            return map;
        }

        if(StringUtils.isBlank(password)){
            map.put("202", "密码不能为空");
            return map;
        }
        try{
            User user = userDAO.selectByName(username);
            if(user.getPassword().equals(password)){
                map.put("000","成功");
                hostHolder.setUser(user);
            }else {
                map.put("301","密码错误");
            }
        }catch (Exception e){
            map.put("400",e.toString());
        }
        return map;
    }

    public Map<String,Object> updateUserPassword(String password){
        Map<String,Object> map = new HashMap<>();
        try {
            User user = hostHolder.getUser();
            user.setPassword(password);
            userDAO.updateUserPassword(user.getUsername(),user.getPassword());
            map.put("000","成功");
        }catch (Exception e){
            map.put("300",e.toString());
        }
        return map;
    }


}
