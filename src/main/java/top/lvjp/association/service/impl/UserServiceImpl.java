package top.lvjp.association.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.form.UserForm;
import top.lvjp.association.mapper.UserMapper;
import top.lvjp.association.entity.User;
import top.lvjp.association.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByNameAndPassword(String name, String password) {
        return userMapper.selectByNameAndPassword(name,password);
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public PageVO<User> selectAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userMapper.selectAll();
        PageInfo<User> pageInfo = new PageInfo<User>(users);
        return new PageVO<User>(pageInfo);
    }

    @Override
    public int insert(UserForm userForm) {
        User user = userMapper.selectByName(userForm.getUserName());
        if (user != null) {
            throw new MyException(ResultEnum.USER_IS_EXISTS);
        }
        return userMapper.insert(userForm);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int deleteById(Integer id) {
        return userMapper.deleteById(id);
    }
}
