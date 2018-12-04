package top.lvjp.association.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lvjp.association.VO.Result;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.form.RightsForm;
import top.lvjp.association.service.RightsService;
import top.lvjp.association.util.ResultUtil;

import javax.validation.Valid;

@RestController
public class RightsController {

    @Autowired
    private RightsService rightsService;

    /**
     * 申请维权
     * @param rightsForm 维权信息
     * @param bindingResult
     * @return
     */
    @PostMapping("/rights/apply")
    public Result applyRights(@Valid RightsForm rightsForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.validError(bindingResult.getFieldError().getDefaultMessage());
        }
        int count = rightsService.insertRights(rightsForm);
        if (count == 1) return ResultUtil.success();
        return ResultUtil.error(ResultEnum.OPERATE_IS_FAIL);
    }
}
