package top.lvjp.association.controller.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.lvjp.association.VO.Result;
import top.lvjp.association.constant.SessionConstant;
import top.lvjp.association.form.QueryForm;
import top.lvjp.association.service.ActivityApplyService;
import top.lvjp.association.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("manage/activity/apply")
public class ManageActivityApplyController {

    @Autowired
    private ActivityApplyService activityApplyService;


    /**
     * 查找当前用户社团的活动的报名表
     * @param activityId 查询的活动的编号
     * @param pageNum 页码
     * @param size 每页数量
     * @param request
     * @return
     */
    @GetMapping("/list")
    public Result selectByActivity(@RequestParam("activityId") Integer activityId,
                                   @RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size,
                                   HttpServletRequest request){
        Integer associationId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        return ResultUtil.success(activityApplyService.selectByActivity(associationId,activityId,pageNum,size));
    }


    /**
     * 查询符合条件的活动报名信息
     * @param queryForm 查询条件 {@link top.lvjp.association.form.QueryForm}
     * @param pageNum
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/query")
    public Result query(QueryForm queryForm, @RequestParam("pageNum") Integer pageNum,
                        @RequestParam("size") Integer size, HttpServletRequest request){
        Integer associationId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        return ResultUtil.success(activityApplyService.query(queryForm,associationId,pageNum,size));
    }
}
