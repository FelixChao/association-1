 package top.lvjp.association.controller.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.lvjp.association.VO.Result;
import top.lvjp.association.constant.SessionConstant;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.form.AssociationForm;
import top.lvjp.association.service.AssociationService;
import top.lvjp.association.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/manage/association")
public class AssociationManageController {

    @Autowired
    private AssociationService associationService;

    /**
     * 更新社团社徽
     * @param associationId 社团编号
     * @param pictureId 新社徽图片编号
     * @param request
     * @return
     */
    @PostMapping("/update/icon")
    public Result updateIcon(@RequestParam("associationId") String associationId,
                             @RequestParam("pictureId") Integer pictureId, HttpServletRequest request){
       String userAssociation = (String) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        if (userAssociation.equals(SessionConstant.ROOT_ASSOCIATION_VALUE) || associationId.equals(userAssociation)){
            int count = associationService.updateAssociationIcon(associationId, pictureId);
            return ResultUtil.success(count);
        }
        return ResultUtil.error(ResultEnum.RIGHTS_NOT_SATISFY);
    }

    /**
     * 更新社团描述
     * @param associationId 社团编号
     * @param description 社团描述
     * @param request
     * @return
     */
    @PostMapping("/update/desc")
    public Result updateDesc(@RequestParam("associationId") String associationId,
                             @RequestParam("description") String description, HttpServletRequest request){
       String userAssociation = (String) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        if (userAssociation.equals(SessionConstant.ROOT_ASSOCIATION_VALUE) || associationId.equals(userAssociation)){
            int count = associationService.updateAssociationDesc(associationId, description);
            return ResultUtil.success(count);
        }
        return ResultUtil.error(ResultEnum.RIGHTS_NOT_SATISFY);
    }

    /**
     * 获取社团信息表单, 用于修改社团信息
     * 需要最高管理权限
     * @param associationId
     * @param request
     * @return
     */
    @GetMapping("/form")
    public Result getForm(@RequestParam("associationId") String associationId, HttpServletRequest request){
       String userAssociation = (String) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        if (userAssociation.equals(SessionConstant.ROOT_ASSOCIATION_VALUE) || associationId.equals(userAssociation)){
            AssociationForm associationForm = associationService.getAssociationForm(associationId);
            return ResultUtil.success(associationForm);
        }
        return ResultUtil.error(ResultEnum.RIGHTS_NOT_SATISFY);
    }


    /**
     * 更新社团信息
     * 需要最高管理员权限
     * @param form 社团信息表单
     * @param bindingResult
     * @return
     */
    @PostMapping("/update")
    public Result updateAssociation(@Valid AssociationForm form, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(ResultEnum.FORM_VALID_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        int count = associationService.updateAssociation(form);
        return ResultUtil.success(count);
    }
}
