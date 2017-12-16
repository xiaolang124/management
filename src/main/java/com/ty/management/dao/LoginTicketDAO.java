package com.ty.management.dao;

import com.ty.management.model.LoginTicket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginTicketDAO {

    void addTicket(@Param("loginTicket") LoginTicket loginTicket);

    LoginTicket selectByTicket(@Param("ticket") String ticket);
}
