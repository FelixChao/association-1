package top.lvjp.association.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lvjp.association.VO.Result;
import top.lvjp.association.form.ActivityApplyForm;
import top.lvjp.association.service.ActivityApplyService;
import top.lvjp.association.util.ResultUtil;

import javax.validation.Valid;

@RestController
public class ActivityApplyController {

    @Autowired
    private ActivityApplyService activityApplyService;

    /**
     * 活动报名
     * @param applyForm 报名表单
     * @param bindingResult
     * @return
     */
    @PostMapping("/activity/apply")
    public Result applyActivity(@Valid ActivityApplyForm applyForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.validError(bindingResult.getFieldError().getDefaultMessage());
        }
        activityApplyService.insertApply(applyForm);
        return ResultUtil.success();
    }
}
