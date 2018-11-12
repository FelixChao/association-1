package top.lvjp.association.service;

import top.lvjp.association.VO.PageVO;
import top.lvjp.association.entity.User;
import top.lvjp.association.form.UserForm;

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
    PageVO<User> selectAll(Integer pageNum, Integer pageSize);

    /**
     * 添加一个用户
     * @param user
     * @return 成功返回1,失败返回0
     */
    int insert(UserForm user);

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
