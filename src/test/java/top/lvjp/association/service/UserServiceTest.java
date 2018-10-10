package top.lvjp.association.service;

import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.lvjp.association.entity.User;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

   @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Test
    public void selectUser() {
        PageInfo<User> pageInfo = userService.selectAll(1,2);
        logger.info("总页数:{} \n 当前页条数:{}",pageInfo.getPages(),pageInfo.getSize());
        List<User> users = pageInfo.getList();
        for (User user: users) {
            logger.info("用户姓名: {}",user.getUserName());
        }
        Assert.assertEquals(2, users.size());
    }
}