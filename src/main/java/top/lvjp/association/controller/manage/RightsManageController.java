package top.lvjp.association.controller.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.Result;
import top.lvjp.association.VO.RightsInfo;
import top.lvjp.association.VO.RightsVO;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.enums.RightsStatusEnum;
import top.lvjp.association.enums.UserTypeEnum;
import top.lvjp.association.service.RightsService;
import top.lvjp.association.util.ResultUtil;
import top.lvjp.association.util.RightsUtil;

import javax.servlet.http.HttpServletRequest;

import static top.lvjp.association.constant.SessionConstant.USER_ASSOCIATION;
import static top.lvjp.association.constant.SessionConstant.USER_TYPE;

@RequestMapping("/manage/rights")
@RestController
public class RightsManageController {

    @Autowired
    private RightsService rightsService;

    /**
     * 获取所有的维权信息
     * @param pageNum
     * @param size
     * @return
     */
    @GetMapping("/list")
    public Result listAll(@RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size){
        PageVO<RightsInfo> rightsInfo = rightsService.listAll(pageNum, size);
        return ResultUtil.success(rightsInfo);
    }

    /**
     * 获取某个社团的相关维权信息
     * @param associationId
     * @param pageNum
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/association")
    public Result listByAssociation(@RequestParam("associationId") String associationId,
                                    @RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size,
                                    HttpServletRequest request){
        String userAssociation = (String) request.getSession().getAttribute(USER_ASSOCIATION);
        Integer userType = (Integer) request.getSession().getAttribute(USER_TYPE);
        if (!RightsUtil.hasRights(userAssociation, associationId, userType)) {
            return ResultUtil.error(ResultEnum.RIGHTS_NOT_SATISFY);
        }
        PageVO<RightsInfo> rightsInfoPageVO = rightsService.listByAssociation(associationId, pageNum, size);
        return ResultUtil.success(rightsInfoPageVO);
    }

    /**
     * 获取某个状态的维权信息, 只能查询本部门的社团维权信息
     * @param status
     * @param pageNum
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/status")
    public Result listByStatus(@RequestParam("status") Integer status,
                               @RequestParam("pageNum") Integer pageNum,
                               @RequestParam("size") Integer size, HttpServletRequest request){
        String userAssociation = (String) request.getSession().getAttribute(USER_ASSOCIATION);
        Integer userType = (Integer) request.getSession().getAttribute(USER_TYPE);
        if (userType.equals(UserTypeEnum.ROOT.getValue())){
            userAssociation = null;
        }
        PageVO<RightsInfo> rightsInfo = rightsService.listByStatus(status, userAssociation, pageNum, size);
        return ResultUtil.success(rightsInfo);
    }

    /**
     * 查看某条维权记录的详细信息
     * @param rightsId
     * @param request
     * @return
     */
    @GetMapping("/detail")
    public Result getById(@RequestParam("id") Integer rightsId, HttpServletRequest request){
        String userAssociation = (String) request.getSession().getAttribute(USER_ASSOCIATION);
        Integer userType = (Integer) request.getSession().getAttribute(USER_TYPE);
        RightsVO rightsVO = rightsService.getById(rightsId, userAssociation, userType);
        return  ResultUtil.success(rightsVO);
    }

    /**
     * 更新某条维权信息, 只能更新自家的
     * @param rightsId
     * @param status 更新维权状态, 1: 正在处理, 2: 已解决
     * @param solution
     * @param request
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestParam("rightsId") Integer rightsId,
                         @RequestParam("status") Integer status,
                         @RequestParam("solution") String solution, HttpServletRequest request){
        String userAssociation = (String) request.getSession().getAttribute(USER_ASSOCIATION);
        Integer userType = (Integer) request.getSession().getAttribute(USER_TYPE);
        if (!status.equals(RightsStatusEnum.PROCESSING.getValue())
                && !status.equals(RightsStatusEnum.RESOLVED.getValue())){
            return ResultUtil.error(ResultEnum.PARAMETERS_IS_ERROR);
        }
        int count = rightsService.update(userAssociation, rightsId, status, solution, userType);
        return ResultUtil.success(count);
    }

    /**
     * 删除维权信息, 要求最高管理员身份, shiro 拦截
     * @param id
     * @param request
     * @return
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") Integer id, HttpServletRequest request){
        int count = rightsService.delete(id);
        return ResultUtil.success(count);
    }
}
