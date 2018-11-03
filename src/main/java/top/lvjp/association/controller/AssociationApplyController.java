package top.lvjp.association.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lvjp.association.VO.Result;
import top.lvjp.association.form.AssociationApplyForm;
import top.lvjp.association.service.AssociationApplyService;
import top.lvjp.association.util.ResultUtil;

import javax.validation.Valid;

@RestController
public class AssociationApplyController {

    @Autowired
    private AssociationApplyService associationApplyService;

    /**
     * 社团报名
     * @param applyForm 报名信息
     * @param bindingResult
     * @return
     */
    @PostMapping("/association/apply")
    public Result applyAssociation(@Valid AssociationApplyForm applyForm,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.validError(bindingResult.getFieldError().getDefaultMessage());
        }
        associationApplyService.insertApply(applyForm);
        return ResultUtil.success();
    }


}
