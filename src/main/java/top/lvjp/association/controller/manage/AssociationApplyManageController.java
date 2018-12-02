package top.lvjp.association.controller.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lvjp.association.VO.Result;
import top.lvjp.association.constant.SessionConstant;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.form.QueryForm;
import top.lvjp.association.service.AssociationApplyService;
import top.lvjp.association.service.AssociationService;
import top.lvjp.association.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/manage/association/apply")
@RestController
public class AssociationApplyManageController {

    @Autowired
    private AssociationApplyService associationApplyService;

    @Autowired
    private AssociationService associationService;


    /**
     * 查询社团报名表
     * 普通用户只能查询本社团的报名表, 超级管理员可指定查询某社团的报名表
     * @param associationId 指定查询的社团编号, 可为null
     * @param pageNum
     * @param size
     * @param request
     * @return
     */
    @GetMapping("list")
    public Result listByAssociation(@RequestParam(value = "associationId", required = false) Integer associationId,
                                    @RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size,
                                    HttpServletRequest request){
        Integer userAssociation = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        if (userAssociation == 0){
            if (associationId == null) {
                return ResultUtil.error(ResultEnum.PARAMETERS_IS_NULL);
            }
            return ResultUtil.success(associationApplyService.listByAssociation(associationId,pageNum,size));
        } else {
            return ResultUtil.success(associationApplyService.listByAssociation(userAssociation,pageNum,size));
        }
    }

    /**
     * 清空社团的报名表
     * 非超级管理员只能清空本社团的报名表
     * @param associationId 要清空报名表的社团编号, 非超级管理员,associationId 需与用户社团编号相同
     * @param request
     * @return
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestParam("associationId") Integer associationId,HttpServletRequest request){
        Integer userAssociation = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        if (userAssociation == 0){
            int count = associationApplyService.delete(associationId);
            return ResultUtil.success(count);
        } else if (userAssociation.equals(associationId)){
            int count = associationApplyService.delete(userAssociation);
            return ResultUtil.success(count);
        } else return ResultUtil.error(ResultEnum.RIGHTS_NOT_SATISFY);
    }

    /**
     * 通过指定条件查询社团报名表
     * @param queryForm
     * @param pageNum
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/query")
    public Result query(QueryForm queryForm, @RequestParam("pageNum") Integer pageNum,
                        @RequestParam("size") Integer size, HttpServletRequest request){
        Integer userAssociation = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        return ResultUtil.success(associationApplyService.query(queryForm,userAssociation,pageNum,size));
    }

    @PostMapping("/enable")
    public Result updateStatus(@RequestParam("associationId") Integer associationId,
            @RequestParam("status") Integer status,HttpServletRequest request){
        Integer userAssociation= (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        if (userAssociation != 0){
            associationId = userAssociation;
        }
        int count = associationService.updateApplyStatus(status, associationId);
        if (count != 0) return ResultUtil.success(count);
        return ResultUtil.error(ResultEnum.OPERATE_IS_FAIL);
    }
}
