package top.lvjp.association.controller.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lvjp.association.VO.Result;
import top.lvjp.association.constant.SessionConstant;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.service.RightsService;
import top.lvjp.association.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;

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
    @GetMapping("/all")
    public Result listAll(@RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size){
        return ResultUtil.success(rightsService.listAll(pageNum, size));
    }

    /**
     * 获取某个社团的相关维权信息
     * @param associationId
     * @param pageNum
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/association/{associationId}")
    public Result listByAssociation(@PathVariable("associationId") Integer associationId,
                                    @RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size,
                                    HttpServletRequest request){
        Integer userAssociatino = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        if (userAssociatino != 0 && userAssociatino != associationId) {
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
    @GetMapping("/status/{status}")
    public Result listByStatus(@PathVariable("status") Integer status,
                               @RequestParam("pageNum") Integer pageNum,
                               @RequestParam("size") Integer size, HttpServletRequest request){
        Integer userAssociation = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        return ResultUtil.success(rightsService.listByStatus(status, userAssociation, pageNum, size));
    }

    /**
     * 查看某条维权记录的详细信息
     * @param rightsId
     * @param request
     * @return
     */
    @GetMapping("/detail/{rightsId}")
    public Result getById(@PathVariable("rightsId") Integer rightsId, HttpServletRequest request){
        Integer userAssociation = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        return  ResultUtil.success(rightsService.getById(rightsId,userAssociation));
    }

    /**
     * 更新某条维权信息
     * @param rightsId
     * @param status
     * @param solution
     * @param request
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestParam("rightsId") Integer rightsId,
                         @RequestParam("status") Integer status,
                         @RequestParam("solution") String solution, HttpServletRequest request){
        Integer userAssociation = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        Integer associationId = null;
        if (userAssociation != 0){
            associationId = userAssociation;
        }
        int rightsStatus;
        if (status == 1) {
            rightsStatus = 1;
        }else if (status == 2){
            rightsStatus = 2;
        } else return ResultUtil.error(ResultEnum.PARAMETERS_IS_ERROR);
        return ResultUtil.success(rightsService.update(associationId, rightsId, rightsStatus, solution));
    }
}
