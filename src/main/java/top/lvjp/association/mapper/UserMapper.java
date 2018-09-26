package top.lvjp.association.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.lvjp.association.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {

    User selectUserByName(String name);

    User selectUserById(Integer id);

    List<User> selectUser();

    int insertUser(User user);

    int updateUser(User user);

    int deleteUserById(Integer id);
}
