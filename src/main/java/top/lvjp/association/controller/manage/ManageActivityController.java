package top.lvjp.association.controller.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.lvjp.association.VO.ActivityInfo;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.Result;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.enums.TextStatusEnum;
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
     * 查询associationId社团的发布状态为status的正在进行活动
     * @param status
     * @param associationId
     * @param pageNum
     * @param size
     * @return
     */
    @GetMapping("/current")
    public Result selectCurrentByStatus(@RequestParam("status") Integer status,
                                        @RequestParam("associationId") Integer associationId,
                                        @RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("size") Integer size){
        PageVO<ActivityInfo> pageInfo = activityService.selectCurrentByStatus(status,associationId,pageNum,size);
        return ResultUtil.success(pageInfo);
    }

    /**
     * 查询associationId社团的发布状态为status的即将进行活动
     * @param status
     * @param associationId
     * @param pageNum
     * @param size
     * @return
     */
    @GetMapping("/future")
    public Result selectFutureByStatus(@RequestParam("status") Integer status,
                                       @RequestParam("associationId") Integer associationId,
                                       @RequestParam("pageNum") Integer pageNum,
                                       @RequestParam("size") Integer size){
       PageVO<ActivityInfo> pageInfo = activityService.selectFutureByStatus(status,associationId,pageNum,size);
       return ResultUtil.success(pageInfo);
    }

    /**
     * 查询associationId社团的发布状态为status的已经结束活动
     * @param status
     * @param associationId
     * @param pageNum
     * @param size
     * @return
     */
    @GetMapping("/past")
    public Result selectPastByStatus(@RequestParam("status") Integer status,
                                     @RequestParam("associationId") Integer associationId,
                                     @RequestParam("pageNum") Integer pageNum,
                                     @RequestParam("size") Integer size){
       PageVO<ActivityInfo> pageInfo = activityService.selectPastByStatus(status,associationId,pageNum,size);
       return ResultUtil.success(pageInfo);
    }

    /**
     * 通过关键字 Key 查找相关活动
     * @param key
     * @param pageNum
     * @param size
     * @return
     */
    @GetMapping("/query")
    public Result queryByKey(@RequestParam("key") String key,
                             @RequestParam("pageNum") Integer pageNum,
                             @RequestParam("size") Integer size, HttpServletRequest request){
        Integer associationId = (Integer)request.getSession().getAttribute("associationId");
        System.out.println(associationId);
        PageVO<ActivityInfo> pageInfo = activityService.queryByKey(key,associationId,pageNum,size);
        return ResultUtil.success(pageInfo);
    }

    /**
     * 发布或存入草稿
     * @param id
     * @param publish
     * @return
     */
    @PostMapping("/publish/{id}")
    public Result publish(@PathVariable Integer id,@RequestParam("publish") Integer publish
            ,HttpServletRequest request){
        int success = 0;
        Integer associationId = (Integer)request.getSession().getAttribute("associationId");
        if(publish.equals(TextStatusEnum.PUBLISH.getStatus())){
            success = activityService.publish(id,TextStatusEnum.PUBLISH,associationId);
        }else if(publish.equals(TextStatusEnum.UNPUBLISH.getStatus())){
            success = activityService.publish(id,TextStatusEnum.UNPUBLISH,associationId);
        }
        if (success == 1) return ResultUtil.success();
        return ResultUtil.error(ResultEnum.OPERATE_IS_FAIL);
    }

    /**
     * 删除活动
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id,HttpServletRequest request){
        int success = 0;
        Integer associationId = (Integer) request.getSession().getAttribute("associationId");
        success = activityService.delete(id,associationId);
        if (success == 1) return ResultUtil.success();
        return ResultUtil.error(ResultEnum.OPERATE_IS_FAIL);
    }

    /**
     * 获取活动的表单信息
     * @param id
     * @return
     */
    @GetMapping("/form/{id}")
    public Result getForm(@PathVariable("id") Integer id){
        return ResultUtil.success(activityService.getForm(id));
    }


    /**
     * 更新活动
     * @param activityForm 提交表单
     * @param bindingResult 表单验证的结果
     * @return
     */
    @PostMapping("/update")
    public Result update(@Valid ActivityForm activityForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.validError(bindingResult.getFieldError().getDefaultMessage());
        }
        int success = activityService.update(activityForm);
        if (success == 1) return ResultUtil.success();
        return ResultUtil.error(ResultEnum.OPERATE_IS_FAIL);
    }

    /**
     * 添加活动
     * @param activityForm 提交的表单
     * @param bindingResult 表单验证的结果
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@Valid ActivityForm activityForm,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.validError(bindingResult.getFieldError().getDefaultMessage());
        }
        int success = activityService.insert(activityForm);
        if (success == 1) return ResultUtil.success();
        return ResultUtil.error(ResultEnum.OPERATE_IS_FAIL);
    }
}
