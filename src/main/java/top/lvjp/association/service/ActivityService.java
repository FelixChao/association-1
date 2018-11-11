package top.lvjp.association.service;

import top.lvjp.association.VO.ActivityInfo;
import top.lvjp.association.enums.TextStatusEnum;
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
    List<ActivityInfo> selectCurrent();

    /**
     * 查询即将开始的活动
     * @return List &lt;ActivityInfo&gt;
     */
    List<ActivityInfo> selectFuture();

    /**
     * 查询已经结束的活动
     * @return List &lt;ActivityInfo&gt;
     */
    List<ActivityInfo> selectPast();

    /**
     * 查询某个社团现在正在进行的活动
     * @return List &lt;ActivityInfo&gt;
     */
    List<ActivityInfo> selectCurrentByAssociation(Integer id);

    /**
     * 查询某个社团即将开始的活动
     * @return List &lt;ActivityInfo&gt;
     */
    List<ActivityInfo> selectFutureByAssociation(Integer id);

    /**
     * 查询某个社团已经结束的活动
     * @return List &lt;ActivityInfo&gt;
     */
    List<ActivityInfo> selectPastByAssociation(Integer id);

    /**
     * 查询associationId社团的发布状态为status的正在进行活动
     * @param status   发布状态
     * @param associationId 社团,如果为0,则查询全部社团
     * @return
     */
    List<ActivityInfo> selectCurrnetByStatus(Integer status,Integer associationId);

    /**
     * 查询associationId社团的发布状态为status的即将进行活动
     * @param status   发布状态
     * @param associationId 社团,如果为0,则查询全部社团
     * @return
     */
    List<ActivityInfo> selectFutureByStatus(Integer status,Integer associationId);

    /**
     * 查询associationId社团的发布状态为status的已经结束的活动
     * @param status   发布状态
     * @param associationId 社团,如果为0,则查询全部社团
     * @return
     */
    List<ActivityInfo> selectPastByStatus(Integer status,Integer associationId);

    /**
     * 通过id 查询某一个活动
     * @param id
     * @return
     */
    ActivityInfo getInfoById(Integer id);

    /**
     * 发布或者取消发布
     * @param id   相关活动编号
     * @param status 发布状态
     * @return
     */
    int publish(Integer id,TextStatusEnum status);

    /**
     * 删除活动
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 获取活动的表单信息,用于修改
     * @param id
     * @return
     */
    ActivityForm getForm(Integer id);

    /**
     * 更新活动内容
     * @param activityForm
     * @return
     */
    int update(ActivityForm activityForm);

    /**
     * 添加活动
     * @param activityForm
     * @return
     */
    int insert(ActivityForm activityForm);

}