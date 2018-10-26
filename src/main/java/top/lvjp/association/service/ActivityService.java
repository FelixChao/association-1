package top.lvjp.association.service;

import top.lvjp.association.VO.ActivityInfo;

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
     * 通过id 查询某一个活动
     * @param id
     * @return
     */
    ActivityInfo getInfoById(Integer id);

}