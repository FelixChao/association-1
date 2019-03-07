package top.lvjp.association.controller.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.Result;
import top.lvjp.association.constant.SessionConstant;
import top.lvjp.association.entity.AssociationApply;
import top.lvjp.association.enums.ApplyStatusEnum;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.enums.UserTypeEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.form.QueryForm;
import top.lvjp.association.service.AssociationApplyService;
import top.lvjp.association.service.AssociationService;
import top.lvjp.association.util.ResultUtil;
import top.lvjp.association.util.RightsUtil;

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
     * 最高管理员可指定查询某社团的报名表, 否则只能查询本社团
     * @param associationId 指定查询的社团编号
     * @param pageNum
     * @param size
     * @param request
     * @return
     */
    @GetMapping("list")
    public Result listByAssociation(@RequestParam("associationId") String associationId,
                                    @RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size,
                                    HttpServletRequest request){
        String userAssociation = (String) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        Integer userType = (Integer) request.getSession().getAttribute(SessionConstant.USER_TYPE);
        if (RightsUtil.hasRights(userAssociation, associationId, userType)){
            PageVO<AssociationApply> applyPageVO = associationApplyService.listByAssociation(associationId, pageNum, size);
            return ResultUtil.success(applyPageVO);
        }
        return ResultUtil.error(ResultEnum.RIGHTS_NOT_SATISFY);
    }

    /**
     * 通过指定条件查询社团报名表
     * 最高管理员可查询指定社团报名表, 其他用户只能在本社团报名表中查询( 强制 )
     * @param queryForm 查询条件的表单
     * @param pageNum
     * @param size
     * @param request 获取用户身份
     * @return
     */
    @GetMapping("/query")
    public Result query(QueryForm queryForm, @RequestParam("pageNum") Integer pageNum,
                        @RequestParam("size") Integer size, HttpServletRequest request){
        queryForm.setAssociationId(null);
        String userAssociation = (String) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        Integer userType = (Integer) request.getSession().getAttribute(SessionConstant.USER_TYPE);
        if (!userType.equals(UserTypeEnum.ROOT.getValue())) {
            queryForm.setAssociationId(userAssociation);
        }
        PageVO<AssociationApply> applyPageVO = associationApplyService.query(queryForm, pageNum, size);
        return ResultUtil.success(applyPageVO);
    }

    /**
     * 更新报名状态
     * 非最高管理员只能更新本社团报名状态
     * @param associationId
     * @param status
     * @param request
     * @return
     */
    @PostMapping("/updateApply")
    public Result updateStatus(@RequestParam("associationId") String associationId,
            @RequestParam("status") Integer status,HttpServletRequest request){
        String userAssociation = (String) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        Integer userType = (Integer) request.getSession().getAttribute(SessionConstant.USER_TYPE);
        if (!ApplyStatusEnum.FORBID_APPLY.getStatus().equals(status)
                && !ApplyStatusEnum.ALLOW_APPLY.getStatus().equals(status)) {
            throw new MyException(ResultEnum.PARAMETERS_IS_ERROR);
        }
        if (RightsUtil.hasRights(userAssociation, associationId, userType)){
            associationService.updateApplyStatus(status, associationId);
            return ResultUtil.success();
        }
        return ResultUtil.error(ResultEnum.RIGHTS_NOT_SATISFY);
    }

    /**
     * 清空社团的报名表
     * 非超级管理员只能清空本社团的报名表
     * @param associationId 要清空报名表的社团编号, 非超级管理员,associationId 需与用户社团编号相同
     * @param request
     * @return
     */
    @DeleteMapping("/clean")
    public Result clean(@RequestParam("associationId") String associationId,HttpServletRequest request){
        String userAssociation = (String) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        Integer userType = (Integer) request.getSession().getAttribute(SessionConstant.USER_TYPE);
        int count;
        if (RightsUtil.hasRights(userAssociation, associationId, userType)){
            count = associationApplyService.deleteAll(associationId);
            return ResultUtil.success(count);
        }
        return ResultUtil.error(ResultEnum.RIGHTS_NOT_SATISFY);
    }

    /**
     * 批量删除报名信息
     * @param ids 报名编号数组
     * @param associationId
     * @param request
     * @return
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestParam("ids") Integer[] ids, @RequestParam("associationId") String associationId,
                         HttpServletRequest request){
        String userAssociation = (String) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        Integer userType = (Integer) request.getSession().getAttribute(SessionConstant.USER_TYPE);
        if (RightsUtil.hasRights(userAssociation, associationId, userType)){
            int count = associationApplyService.deleteByIds(ids, associationId);
            return ResultUtil.success(count);
        }
        return ResultUtil.error(ResultEnum.RIGHTS_NOT_SATISFY);
    }
}
