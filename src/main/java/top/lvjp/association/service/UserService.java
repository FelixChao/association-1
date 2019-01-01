package top.lvjp.association.service;

import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.UserVO;
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

//    /**
//     * 通过id查询用户
//     * @param id
//     * @return user
//     */
//    User getById(Integer id);

    /**
     * 查询所有用户
     * @return List &lt UserVO &gt
     */
    PageVO<UserVO> listAll(Integer pageNum, Integer pageSize);

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
    int update(UserForm user);

    /**
     * 删除一个用户
     * @param id
     * @return 成功返回1,失败返回0
     */
    int deleteById(Integer id);

    /**
     * 获取用户信息
     * @param userId
     * @return UserVO
     */
    UserVO getUserVO(Integer userId);
}
