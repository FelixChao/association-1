package top.lvjp.association.mapper;

import org.apache.ibatis.annotations.Param;
import top.lvjp.association.entity.User;

import java.util.List;

public interface UserMapper {

    User selectByNameAndPassword(@Param("name") String name, @Param("password") String password);

    User selectById(Integer id);

    List<User> selectAll();

    int insert(User user);

    int update(User user);

    int deleteById(Integer id);
}