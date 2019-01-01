package top.lvjp.association.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.lvjp.association.VO.UserVO;

@SpringBootTest
@RunWith(SpringRunner.class)
@MapperScan("top.lvjp.association.mapper")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUserVO() {
        UserVO userVO = userService.getUserVO(1);
        System.out.println(userVO.getAssociationName());
    }
}