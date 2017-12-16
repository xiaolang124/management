package com.ty.management.database;

import com.ty.management.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/init-schema.sql")
public class InitDatabaseTests {

    @Autowired
    UserService userService;

    @Test
    public void initDatabase() {
        userService.register("abc","bcd");
        userService.login("abc","bcd");
    }
}
