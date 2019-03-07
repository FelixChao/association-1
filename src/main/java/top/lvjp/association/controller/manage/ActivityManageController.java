package top.lvjp.association.controller.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.lvjp.association.VO.ActivityInfo;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.Result;
import top.lvjp.association.constant.SessionConstant;
import top.lvjp.association.enums.UserTypeEnum;
import top.lvjp.association.form.ActivityForm;
import top.lvjp.association.service.ActivityService;
import top.lvjp.association.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/manage/activity")
public class ActivityManageController {

    @Autowired
    private ActivityService activityService;

    /**
     * 查询本社团的发布状态为 status 的正在进行活动
     * 最高管理员查询全部社团活动, 其他用户查询结果为本社团活动
     * @param status 活动发布状态
     * @param pageNum 当前页
     * @param size 每页显示数量
     * @param request
     * @return PageVO&lt;ActivityInfo&gt;
     */
    @GetMapping("/current")
    public Result listCurrentByStatus(@RequestParam("status") Integer status,
                                      @RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("size") Integer size,HttpServletRequest request){
        String userAssociation = (String) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        Integer userType = (Integer) request.getSession().getAttribute(SessionConstant.USER_TYPE);
        if (userType.equals(UserTypeEnum.ROOT.getValue())){
            userAssociation = null;
        }
        PageVO<ActivityInfo> pageInfo = activityService.selectCurrentByStatus(status, userAssociation, pageNum, size);
        return ResultUtil.success(pageInfo);
    }

    /**
     * 查询本社团的发布状态为 status 的即将进行活动
     * 最高管理员查询全部社团活动, 其他用户查询结果为本社团活动
     * @param status 活动发布状态
     * @param pageNum 当前页
     * @param size 每页显示数量
     * @return PageVO&lt;ActivityInfo&gt;
     */
    @GetMapping("/future")
    public Result listFutureByStatus(@RequestParam("status") Integer status,
                                     @RequestParam("pageNum") Integer pageNum,
                                     @RequestParam("size") Integer size,HttpServletRequest request){
        String userAssociation = (String) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        Integer userType = (Integer) request.getSession().getAttribute(SessionConstant.USER_TYPE);
        if (userType.equals(UserTypeEnum.ROOT.getValue())){
            userAssociation = null;
        }
        PageVO<ActivityInfo> pageInfo = activityService.selectFutureByStatus(status,userAssociation,pageNum,size);
       return ResultUtil.success(pageInfo);
    }

    /**
     * 查询本社团的发布状态为 status 的已经结束活动
     * 最高管理员查询全部社团活动, 其他用户查询结果为本社团活动
     * @param status 活动发布状态
     * @param pageNum 当前页
     * @param size 每页显示数量
     * @return PageVO&lt;ActivityInfo&gt;
     */
    @GetMapping("/past")
    public Result listPastByStatus(@RequestParam("status") Integer status,
                                   @RequestParam("pageNum") Integer pageNum,
                                   @RequestParam("size") Integer size, HttpServletRequest request){
        String userAssociation = (String) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        Integer userType = (Integer) request.getSession().getAttribute(SessionConstant.USER_TYPE);
        if (userType.equals(UserTypeEnum.ROOT.getValue())){
            userAssociation = null;
        }
        PageVO<ActivityInfo> pageInfo = activityService.selectPastByStatus(status ,userAssociation, pageNum, size);
        return ResultUtil.success(pageInfo);
    }

    /**
     * 通过关键字 Key 在本社团查找相关活动
     * @param key 查询关键字
     * @param pageNum 当前页
     * @param size 每页显示数量
     * @param request
     * @return PageVO&lt;ActivityInfo&gt;
     */
    @GetMapping("/query")
    public Result queryByKey(@RequestParam("key") String key,
                             @RequestParam("pageNum") Integer pageNum,
                             @RequestParam("size") Integer size, HttpServletRequest request){
        String userAssociation = (String) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        Integer userType = (Integer) request.getSession().getAttribute(SessionConstant.USER_TYPE);
        if (userType.equals(UserTypeEnum.ROOT.getValue())){
            userAssociation = null;
        }
        PageVO<ActivityInfo> pageInfo = activityService.queryByKey(key,userAssociation,pageNum,size);
        return ResultUtil.success(pageInfo);
    }

    /**
     * 发布本社团活动
     * @param id 选择的活动的编号
     * @return 成功返回success,失败则返回 操作失败,或者抛出异常
     */
    @PostMapping("/publish")
    public Result publish(@RequestParam("id") Integer id,HttpServletRequest request){
        String userAssociation = (String) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        Integer userType = (Integer) request.getSession().getAttribute(SessionConstant.USER_TYPE);
        int count = activityService.publish(id, userAssociation, userType);
        return ResultUtil.success(count);
    }

    /**
     * 删除本社团的活动
     * @param id 删除活动的编号
     * @param request 发送的请求
     * @return 成功返回success,失败则返回 操作失败,或者抛出异常
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") Integer id,HttpServletRequest request){
        String userAssociation = (String) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        Integer userType = (Integer) request.getSession().getAttribute(SessionConstant.USER_TYPE);
        int count = activityService.delete(id, userAssociation, userType);
        return ResultUtil.success(count);
    }

    /**
     * 获取活动的表单信息
     * @param id 查询表单的id
     * @return ActivityForm
     */
    @GetMapping("/form")
    public Result getForm(@RequestParam("id") Integer id){
        ActivityForm activityForm = activityService.getForm(id);
        return ResultUtil.success(activityForm);
    }

    /**
     * 更新活动
     * 非最高管理员只能更新本社团的活动
     * @param activityForm 提交表单
     * @param bindingResult 表单验证的结果
     * @return 成功返回success,失败则返回 操作失败,或者抛出异常
     */
    @PostMapping("/update")
    public Result update(@Valid ActivityForm activityForm, BindingResult bindingResult,
                         HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return ResultUtil.validError(bindingResult.getFieldError().getDefaultMessage());
        }
        String userAssociation = (String) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        Integer userType = (Integer) request.getSession().getAttribute(SessionConstant.USER_TYPE);
        int count = activityService.update(activityForm, userAssociation, userType);
        return ResultUtil.success(count);
    }

//    @PostMapping("/picture/update")
//    public Result updateActivityIcon(@RequestParam("activityId") Integer activityId,
//                                     @RequestParam("pictureId") Integer pictureId,HttpServletRequest request){
//        String userAssociation = (String) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
//        activityService.updateActivityIcon(activityId, pictureId, userAssociation);
//        return ResultUtil.success();
//    }

    /**
     * 添加活动
     * 非最高管理员只能添加本社团活动
     * @param activityForm 提交的表单
     * @param bindingResult 表单验证的结果
     * @return 成功返回success,失败则返回 操作失败,或者抛出异常
     */
    @PostMapping("/insert")
    public Result insert(@Valid ActivityForm activityForm,BindingResult bindingResult,HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return ResultUtil.validError(bindingResult.getFieldError().getDefaultMessage());
        }
        String userAssociation = (String) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        Integer userType = (Integer) request.getSession().getAttribute(SessionConstant.USER_TYPE);
        activityService.insert(activityForm, userAssociation, userType);
        return ResultUtil.success();
    }
}