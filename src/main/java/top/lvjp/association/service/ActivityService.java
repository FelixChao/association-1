package top.lvjp.association.service;

import top.lvjp.association.VO.ActivityInfo;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.dto.ActivitiesDTO;
import top.lvjp.association.form.ActivityForm;

import java.util.List;

public interface ActivityService {

    /**
     * 查询最新活动
     * @param count 查询数量
     * @return  List &lt;ActivityInfo&gt;
     */
    List<ActivityInfo> selectLatest(Integer count);

    /**
     * 查询现在正在进行的活动
     * @return List &lt;ActivityInfo&gt;
     */
    PageVO<ActivityInfo> selectCurrent(Integer pageNum, Integer size);

    /**
     * 查询即将开始的活动
     * @return List &lt;ActivityInfo&gt;
     */
    PageVO<ActivityInfo> selectFuture(Integer pageNum, Integer size);

    /**
     * 查询已经结束的活动
     * @return List &lt;ActivityInfo&gt;
     */
    PageVO<ActivityInfo> selectPast(Integer pageNum, Integer size);

    /**
     * 查询所有活动, 将结果按活动状态封装在 ActivitiesDTO 中
     * @return
     */
    ActivitiesDTO listAll();

    /**
     * 查询某个社团现在正在进行的活动
     * @return List &lt;ActivityInfo&gt;
     */
    List<ActivityInfo> selectCurrentByAssociation(String id);

    /**
     * 查询某个社团即将开始的活动
     * @return List &lt;ActivityInfo&gt;
     */
    List<ActivityInfo> selectFutureByAssociation(String id);

    /**
     * 查询某个社团已经结束的活动
     * @return List &lt;ActivityInfo&gt;
     */
    List<ActivityInfo> selectPastByAssociation(String id);

    /**
     * 查询associationId社团的发布状态为 status 的正在进行活动
     * @param status   发布状态
     * @param associationId 社团,如果为 null,则查询全部社团
     * @return PageInfo&lt;ActivityInfo&gt;
     */
    PageVO<ActivityInfo> selectCurrentByStatus(Integer status, String associationId, Integer pageNum, Integer size);

    /**
     * 查询associationId社团的发布状态为 status 的即将进行活动
     * @param status 发布状态
     * @param associationId 社团,如果为 null, 则查询全部社团
     * @return PageInfo&lt;ActivityInfo&gt;
     */
    PageVO<ActivityInfo> selectFutureByStatus(Integer status, String associationId, Integer pageNum, Integer size);

    /**
     * 查询 associationId 社团的发布状态为 status 的已经结束的活动
     * @param status   发布状态
     * @param associationId 社团,如果为 null, 则查询全部社团
     * @return PageInfo&lt;ActivityInfo&gt;
     */
    PageVO<ActivityInfo> selectPastByStatus(Integer status, String associationId, Integer pageNum, Integer size);

    /**
     * 通过id 查询某一个活动
     * @param  id 查询活动的编号
     * @return ActivityInfo
     */
    ActivityInfo getInfoById(Integer id);

    /**
     * 获取当前用户的社团所有的活动,(只返回标题)
     * @param associationId 所属社团
     * @param status 活动发布状态
     * @return activityInfo
     */
    List<ActivityInfo> selectAll(String associationId,Integer status);

    /**
     * 通过关键字查找本社团的活动
     * @param key 查找关键字
     * @param associationId 执行操作的用户所属社团
     * @return PageInfo&lt;ActivityInfo&gt;
     */
    PageVO<ActivityInfo> queryByKey(String key,String associationId,Integer pageNum,Integer size);

    /**
     * 发布或者取消发布
     * @param id   相关活动编号
     * @param associationId 用户社团
     * @param userType 用户类型
     * @return 成功返回1,否则0
     */
    int publish(Integer id,String associationId, Integer userType);

    /**
     * 删除活动,且只能删除所属部门的活动
     * 同时删除该活动的报名表
     * @param activityId 需要删除的活动的编号
     * @param associationId 执行操作的用户所属社团
     * @param userType
     * @return
     */
    int delete(Integer activityId,String associationId, Integer userType);

    /**
     * 获取活动的表单信息,用于修改
     * @param id 获取的活动的编号
     * @return ActivityForm
     */
    ActivityForm getForm(Integer id);

    /**
     * 更新活动内容
     * @param activityForm 表单信息
     * @param associationId 用户所属社团
     * @return 成功返回1,否则0
     */
    int update(ActivityForm activityForm, String associationId, Integer userType);

    /**
     * 添加活动
     * @param activityForm 活动信息
     * @param userAssociation
     * @param userType
     * @return 成功返回1,否则返回0
     */
    int insert(ActivityForm activityForm, String userAssociation, Integer userType);

    int updateActivityIcon(Integer activityId, Integer pictureId, String userAssociation, Integer userType);
}