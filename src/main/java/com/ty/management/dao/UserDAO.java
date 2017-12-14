package com.ty.management.dao;

import com.ty.management.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDAO {

    void addUser(@Param("user") User user);

    User selectByName(@Param("username") String username);

    void updateUserPassword(@Param("username") String username,
                            @Param("password") String password);
}
