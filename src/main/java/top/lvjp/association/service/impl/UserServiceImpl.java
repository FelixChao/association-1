package top.lvjp.association.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.UserVO;
import top.lvjp.association.entity.User;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.form.UserForm;
import top.lvjp.association.mapper.UserMapper;
import top.lvjp.association.service.UserService;
import top.lvjp.association.util.MD5Util;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private AssociationMapper associationMapper;

//    @Override
//    public User selectByNameAndPassword(String name, String password) {
//        return userMapper.getByNameAndPassword(name,password);
//    }

//    @Override
//    public User getById(Integer id) {
//        return userMapper.getById(id);
//    }

    @Override
    public PageVO<UserVO> listAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserVO> users = userMapper.listAll();
        PageInfo<UserVO> pageInfo = new PageInfo<>(users);
        return new PageVO<>(pageInfo);
    }

    @Override
    @Transactional
    public int insert(UserForm userForm) {
        User user = userMapper.getByName(userForm.getUserName());
        if (user != null) {
            throw new MyException(ResultEnum.NAME_IS_EXISTS);
        }
        userForm.setSalt(MD5Util.getSalt());
        userForm.setUserPassword(MD5Util.hashed(userForm.getUserPassword(), userForm.getSalt()));
        return userMapper.insert(userForm);
    }

    @Override
    public int update(UserForm userForm) {
        User user = userMapper.getById(userForm.getUserId());
        userForm.setUserPassword(MD5Util.hashed(userForm.getUserPassword(), user.getSalt()));
        return userMapper.update(userForm);
    }

    @Override
    public int deleteById(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    public UserVO getUserVO(Integer userId) {
        return userMapper.getVOById(userId);
    }
}
