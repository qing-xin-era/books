package com.qing.springboot01;

import com.qing.Dao.UserMapper;
import com.qing.pojo.TransactionRecord;
import com.qing.pojo.User;
import com.qing.service.UserService;
import com.qing.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;


@SpringBootTest
class SpringBoot01ApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() throws SQLException {
        User user=userMapper.queryUserById(111);
        List<TransactionRecord> trans = user.getTrans();
        System.out.println(trans);


    }

}
