package top.lvjp.association.controller.manage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.Result;
import top.lvjp.association.constant.SessionConstant;
import top.lvjp.association.entity.ActivityApply;
import top.lvjp.association.form.QueryForm;
import top.lvjp.association.service.ActivityApplyService;
import top.lvjp.association.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("manage/activity/apply")
@Slf4j
public class ActivityApplyManageController {

    @Autowired
    private ActivityApplyService activityApplyService;

    /**
     * 查找活动的报名表
     * @param activityId 查询的活动的编号
     * @param pageNum 页码
     * @param size 每页数量
     * @param request 获取当前用户身份
     * @return
     */
    @GetMapping("/list")
    public Result selectByActivity(@RequestParam("activityId") Integer activityId,
                                   @RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size,
                                   HttpServletRequest request){
        String userAssociation = request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION).toString();
        PageVO<ActivityApply> activityApply = activityApplyService.getByActivity(userAssociation, activityId, pageNum, size);
        return ResultUtil.success(activityApply);
    }

    /**
     * 查询符合条件的活动报名信息
     * 非最高管理员只能在本社团活动内的报名表查询
     * @param queryForm 查询条件 {@link top.lvjp.association.form.QueryForm}
     * @param pageNum
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/query")
    public Result query(QueryForm queryForm, @RequestParam("pageNum") Integer pageNum,
                        @RequestParam("size") Integer size, HttpServletRequest request){
        String userAssociation = request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION).toString();
        PageVO<ActivityApply> activityApply = activityApplyService.query(queryForm, userAssociation, pageNum, size);
        return ResultUtil.success(activityApply);
    }

    /**
     * 删除本社团某活动的报名表
     * @param activityId 活动 id
     * @param request
     * @return
     */
    @DeleteMapping("/clean")
    public Result clean(@RequestParam("activityId") Integer activityId, HttpServletRequest request){
        String userAssociation = request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION).toString();
        int count = activityApplyService.deleteByActivityId(activityId, userAssociation);
        return ResultUtil.success(count);
    }

    /**
     * 批量删除报名信息
     * @param ids 要删除的报名信息 id
     * @param activityId 报名的活动的 id
     * @param request
     * @return
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestParam("ids") Integer[] ids,@RequestParam("activityId") Integer activityId,
                         HttpServletRequest request){
        String userAssociation = request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION).toString();
        int count = activityApplyService.deleteByIds(ids, activityId, userAssociation);
        return ResultUtil.success(count);
    }
}
