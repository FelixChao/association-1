package top.lvjp.association.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.exception.AssociationException;
import top.lvjp.association.mapper.UserMapper;
import top.lvjp.association.entity.User;
import top.lvjp.association.service.UserService;

import javax.validation.Valid;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserByName(String name) {
        return userMapper.selectUserByName(name);
    }

    @Override
    public User selectUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public List<User> selectUser() {
        return userMapper.selectUser();
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int updateUser( User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }
}
