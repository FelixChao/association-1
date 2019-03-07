package top.lvjp.association.mapper;

import top.lvjp.association.VO.UserVO;
import top.lvjp.association.entity.User;
import top.lvjp.association.form.UserForm;

import java.util.List;

public interface UserMapper {

//    User getByNameAndPassword(@Param("name") String name, @Param("password") String password);

    User getById(Integer id);

    UserVO getVOById(Integer id);

    List<UserVO> listAll();

    int insert(UserForm user);

    int update(UserForm user);

    int deleteById(Integer id);

    User getByName(String name);
}
