package top.lvjp.association.service;

import top.lvjp.association.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 通过名字查询用户
     * @param name
     * @return user
     */
    User selectUserByName(String name);

    /**
     * 通过id查询用户
     * @param id
     * @return user
     */
    User selectUserById(Integer id);

    /**
     * 查询所有用户
     * @return List<User>
     */
    List<User> selectUser();

    /**
     * 添加一个用户
     * @param user
     * @return 成功返回1,失败返回0
     */
    int insertUser(User user);

    /**
     * 更新一个用户
     * @param user
     * @return 成功返回1,失败返回0
     */
    int updateUser(User user);

    /**
     * 删除一个用户
     * @param id
     * @return 成功返回1,失败返回0
     */
    int deleteUserById(Integer id);
}
