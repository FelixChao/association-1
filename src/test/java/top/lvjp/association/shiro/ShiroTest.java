package top.lvjp.association.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.lvjp.association.entity.User;
import top.lvjp.association.mapper.UserMapper;

//@SpringBootTest
//@RunWith(SpringRunner.class)
//@MapperScan("top.lvjp.association.mapper")
public class ShiroTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void getUser(){
        User userInfo = userMapper.getByName("root");
        Assert.assertEquals("最高管理员", userInfo.getUserType());
    }

    @Test
    public void getHashCode(){
        String[] passwords = {"123456","aaa"};
        String salt = null;
//        for (String pass : passwords) {
//            salt = MD5Util.getSalt();
            System.out.println(salt + " : " + new Md5Hash("aaa", ByteSource.Util.bytes("123")));

    }
}
