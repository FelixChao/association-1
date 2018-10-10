package top.lvjp.association.service;

import com.github.pagehelper.PageInfo;
import top.lvjp.association.entity.User;

public interface UserService {

    /**
     * 通过名字查询用户
     * @param name
     * @param password
     * @return user
     */
    User selectByNameAndPassword(String name, String password);

    /**
     * 通过id查询用户
     * @param id
     * @return user
     */
    User selectById(Integer id);

    /**
     * 查询所有用户
     * @return List &lt User &gt
     */
    PageInfo<User> selectAll(Integer pageNum, Integer pageSize);

    /**
     * 添加一个用户
     * @param user
     * @return 成功返回1,失败返回0
     */
    int insert(User user);

    /**
     * 更新一个用户
     * @param user
     * @return 成功返回1,失败返回0
     */
    int update(User user);

    /**
     * 删除一个用户
     * @param id
     * @return 成功返回1,失败返回0
     */
    int deleteById(Integer id);
}
