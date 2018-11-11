package top.lvjp.association.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.lvjp.association.VO.ActivityInfo;
import top.lvjp.association.VO.Result;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.enums.TextStatusEnum;
import top.lvjp.association.form.ActivityForm;
import top.lvjp.association.service.ActivityService;
import top.lvjp.association.util.ResultUtil;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/manage/activity")
public class ManageActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 查询associationId社团的发布状态为status的正在进行活动
     * @param status
     * @param associationId
     * @return
     */
    @GetMapping("/current")
    public Result selectCurrnetByStatus(@RequestParam("status") Integer status, @RequestParam("associationId") Integer associationId){
        List<ActivityInfo> activityInfos = activityService.selectCurrnetByStatus(status,associationId);
        return ResultUtil.success(activityInfos);
    }

    /**
     * 查询associationId社团的发布状态为status的即将进行活动
     * @param status
     * @param associationId
     * @return
     */
    @GetMapping("/future")
    public Result selectFutureByStatus(@RequestParam("status") Integer status,@RequestParam("associationId") Integer associationId){
       List<ActivityInfo> activityInfos = activityService.selectFutureByStatus(status,associationId);
       return ResultUtil.success(activityInfos);
    }

    /**
     * 查询associationId社团的发布状态为status的已经结束活动
     * @param status
     * @param associationId
     * @return
     */
    @GetMapping("/past")
    public Result selectPastByStatus(@RequestParam("status") Integer status,@RequestParam("associationId") Integer associationId){
       List<ActivityInfo> activityInfos = activityService.selectPastByStatus(status,associationId);
       return ResultUtil.success(activityInfos);
    }

    /**
     * 发布或存入草稿
     * @param id
     * @param publish
     * @return
     */
    @PostMapping("/publish/{id}")
    public Result publish(@PathVariable Integer id,@RequestParam("publish") Integer publish){
        int success = 0;
        if(publish.equals(TextStatusEnum.PUBLISH.getStatus())){
            success = activityService.publish(id,TextStatusEnum.PUBLISH);
        }else if(publish.equals(TextStatusEnum.UNPUBLISH.getStatus())){
            success = activityService.publish(id,TextStatusEnum.UNPUBLISH);
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
    public Result delete(@PathVariable Integer id){
        int success = 0;
        success = activityService.delete(id);
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
