package top.lvjp.association.controller.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.lvjp.association.VO.ActivityInfo;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.Result;
import top.lvjp.association.constant.SessionConstant;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.form.ActivityForm;
import top.lvjp.association.service.ActivityService;
import top.lvjp.association.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/manage/activity")
public class ManageActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 查询 associationId 社团的发布状态为 status 的正在进行活动
     * @param status 活动发布状态
     * @param pageNum 当前页
     * @param size 每页显示数量
     * @return PageVO&lt;ActivityInfo&gt;
     */
    @GetMapping("/current")
    public Result selectCurrentByStatus(@RequestParam("status") Integer status,
                                        @RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("size") Integer size,HttpServletRequest request){
        Integer associationId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        PageVO<ActivityInfo> pageInfo = activityService.selectCurrentByStatus(status,associationId,pageNum,size);
        return ResultUtil.success(pageInfo);
    }

    /**
     * 查询associationId社团的发布状态为status的即将进行活动
     * @param status 活动发布状态
     * @param pageNum 当前页
     * @param size 每页显示数量
     * @return PageVO&lt;ActivityInfo&gt;
     */
    @GetMapping("/future")
    public Result selectFutureByStatus(@RequestParam("status") Integer status,
                                       @RequestParam("pageNum") Integer pageNum,
                                       @RequestParam("size") Integer size,HttpServletRequest request){
        Integer associationId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        PageVO<ActivityInfo> pageInfo = activityService.selectFutureByStatus(status,associationId,pageNum,size);
       return ResultUtil.success(pageInfo);
    }

    /**
     * 查询associationId社团的发布状态为status的已经结束活动
     * @param status 活动发布状态
     * @param pageNum 当前页
     * @param size 每页显示数量
     * @return PageVO&lt;ActivityInfo&gt;
     */
    @GetMapping("/past")
    public Result selectPastByStatus(@RequestParam("status") Integer status,
                                     @RequestParam("pageNum") Integer pageNum,
                                     @RequestParam("size") Integer size, HttpServletRequest request){
        Integer associationId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        PageVO<ActivityInfo> pageInfo = activityService.selectPastByStatus(status,associationId,pageNum,size);
        return ResultUtil.success(pageInfo);
    }

    /**
     * 通过关键字 Key 查找相关活动
     * @param key 查询关键字
     * @param pageNum 当前页
     * @param size 每页显示数量
     * @return PageVO&lt;ActivityInfo&gt;
     */
    @GetMapping("/query")
    public Result queryByKey(@RequestParam("key") String key,
                             @RequestParam("pageNum") Integer pageNum,
                             @RequestParam("size") Integer size, HttpServletRequest request){
        Integer associationId = (Integer)request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        PageVO<ActivityInfo> pageInfo = activityService.queryByKey(key,associationId,pageNum,size);
        return ResultUtil.success(pageInfo);
    }

    /**
     * 发布
     * @param id 选择的活动的编号
     * @return 成功返回success,失败则返回 操作失败,或者抛出异常
     */
    @PostMapping("/publish/{id}")
    public Result publish(@PathVariable Integer id,HttpServletRequest request){
        int success;
        Integer associationId = (Integer)request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        success = activityService.publish(id,associationId);
        if (success == 1) return ResultUtil.success();
        return ResultUtil.error(ResultEnum.OPERATE_IS_FAIL);
    }

    /**
     * 删除活动
     * @param id 删除活动的编号
     * @param request 发送的请求
     * @return 成功返回success,失败则返回 操作失败,或者抛出异常
     */
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id,HttpServletRequest request){
        int success;
        Integer associationId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        success = activityService.delete(id,associationId);
        if (success != 0) return ResultUtil.success();
        return ResultUtil.error(ResultEnum.OPERATE_IS_FAIL);
    }

    /**
     * 获取活动的表单信息
     * @param id 查询表单的id
     * @return ActivityForm
     */
    @GetMapping("/form/{id}")
    public Result getForm(@PathVariable("id") Integer id){
        return ResultUtil.success(activityService.getForm(id));
    }


    /**
     * 更新活动
     * @param activityForm 提交表单
     * @param bindingResult 表单验证的结果
     * @return 成功返回success,失败则返回 操作失败,或者抛出异常
     */
    @PostMapping("/update")
    public Result update(@Valid ActivityForm activityForm,BindingResult bindingResult,HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return ResultUtil.validError(bindingResult.getFieldError().getDefaultMessage());
        }
        Integer associationId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        if (associationId != 0){
            activityForm.setAssociationId(associationId);
        }
        int success = activityService.update(activityForm);
        if (success == 1) return ResultUtil.success();
        return ResultUtil.error(ResultEnum.OPERATE_IS_FAIL);
    }

    /**
     * 添加活动,如何用户associationId不为0,只能发布本社团的活动
     * @param activityForm 提交的表单
     * @param bindingResult 表单验证的结果
     * @return 成功返回success,失败则返回 操作失败,或者抛出异常
     */
    @PostMapping("/insert")
    public Result insert(@Valid ActivityForm activityForm,BindingResult bindingResult,HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return ResultUtil.validError(bindingResult.getFieldError().getDefaultMessage());
        }
        Integer associationId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        if (associationId != 0){
            activityForm.setAssociationId(associationId);
        }
        int success = activityService.insert(activityForm);
        if (success == 1) return ResultUtil.success();
        return ResultUtil.error(ResultEnum.OPERATE_IS_FAIL);
    }
}
