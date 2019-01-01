package top.lvjp.association.controller.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.Result;
import top.lvjp.association.VO.RightsInfo;
import top.lvjp.association.VO.RightsVO;
import top.lvjp.association.constant.SessionConstant;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.service.RightsService;
import top.lvjp.association.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;

import static top.lvjp.association.constant.SessionConstant.ROOT_ASSOCIATION_VALUE;
import static top.lvjp.association.constant.SessionConstant.USER_ASSOCIATION;

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
        if (!userAssociation.equals(SessionConstant.ROOT_ASSOCIATION_VALUE)
                && !userAssociation.equals(associationId)) {
            return ResultUtil.error(ResultEnum.RIGHTS_NOT_SATISFY);
        }
        return ResultUtil.success(rightsService.listByAssociation(associationId, pageNum, size));
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
        if (userAssociation.equals(SessionConstant.ROOT_ASSOCIATION_VALUE)){
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
        RightsVO rightsVO = rightsService.getById(rightsId,userAssociation);
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
        if (status != 1 && status != 2){
            return ResultUtil.error(ResultEnum.PARAMETERS_IS_ERROR);
        }
        int count = rightsService.update(userAssociation, rightsId, status, solution);
        return ResultUtil.success(count);
    }

    /**
     * 删除维权信息, 要求最高管理员身份
     * @param id
     * @param request
     * @return
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") Integer id, HttpServletRequest request){
        String userAssociation = (String) request.getSession().getAttribute(USER_ASSOCIATION);
        if (!userAssociation.equals(ROOT_ASSOCIATION_VALUE)){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY);
        }
        int count = rightsService.delete(id);
        return ResultUtil.success(count);
    }
}
