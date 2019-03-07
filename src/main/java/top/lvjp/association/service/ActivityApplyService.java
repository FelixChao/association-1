package top.lvjp.association.service;

import top.lvjp.association.VO.PageVO;
import top.lvjp.association.entity.ActivityApply;
import top.lvjp.association.form.ActivityApplyForm;
import top.lvjp.association.form.QueryForm;

public interface ActivityApplyService {

    /**
     * 报名活动
     * @param  apply
     */
    int insertApply(ActivityApplyForm apply);

    /**
     * 查询某个活动的报名表
     * 非最高管理员只能查询属于本部门的活动报名表
     * @param userAssociation 执行该方法的用户所属社团
     * @param activityId 需要查找的活动编号
     * @param pageNum
     * @param size
     * @return
     */
    PageVO<ActivityApply> getByActivity(String userAssociation, Integer userType, Integer activityId, Integer pageNum, Integer size);

    /**
     * 通过查询条件查询本社团的活动报名表
     * @param queryForm 查询表单
     * @param associationId 执行操作的用户所属社团
     * @param userType
     * @param pageNum
     * @param size
     * @return
     */
    PageVO<ActivityApply> query(QueryForm queryForm, String associationId, Integer userType, Integer pageNum, Integer size);

    /**
     * 删除某个活动的全部报名信息
     * @param activityId
     * @param userAssociation
     * @return
     */
    int deleteByActivityId(Integer activityId, String userAssociation, Integer userType);

    /**
     * 删除指定报名信息
     * @param ids 需要删除的报名信息的编号
     * @param activityId
     * @param userAssociation
     * @return
     */
    int deleteByIds(Integer[] ids, Integer activityId, String userAssociation, Integer userType);
}
