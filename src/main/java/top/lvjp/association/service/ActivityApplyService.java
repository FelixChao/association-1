package top.lvjp.association.service;

import top.lvjp.association.VO.PageVO;
import top.lvjp.association.entity.ActivityApply;
import top.lvjp.association.form.ActivityApplyForm;
import top.lvjp.association.form.QueryForm;

public interface ActivityApplyService {

    void insertApply(ActivityApplyForm apply);

    /**
     * 查询某个社团某个活动的报名表
     * @param associationId
     * @param activityId
     * @param pageNum
     * @param size
     * @return
     */
    PageVO<ActivityApply> selectByActivity(Integer associationId, Integer activityId, Integer pageNum, Integer size);

    /**
     * 通过查询条件查询本社团的活动报名表
     * @param queryForm 查询表单
     * @param associationId
     * @return
     */
    PageVO<ActivityApply> query(QueryForm queryForm, Integer associationId, Integer pageNum, Integer size);
}
