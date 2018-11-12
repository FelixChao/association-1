package top.lvjp.association.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.lvjp.association.VO.AssociationInfo;
import top.lvjp.association.VO.AssociationVO;
import top.lvjp.association.VO.Result;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.service.AssociationService;
import top.lvjp.association.util.ResultUtil;

import java.util.List;

@RestController
@RequestMapping("/association")
public class AssociationController {

    @Autowired
    private AssociationService associationService;

    /**
     * 查询社团详细信息
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Result getDetail(@RequestParam("id") Integer id){
        AssociationVO associationVO = associationService.getVOById(id);
        if (associationVO == null) {
            return ResultUtil.error(ResultEnum.RESULT_IS_NULL);
        }
        return ResultUtil.success(associationVO);
    }

    /**
     * 查询热门的社团
     * @param count
     * @return
     */
    @GetMapping("/pop")
    public Result getPop(@RequestParam(value = "count",required = false, defaultValue = "10") Integer count){
        List<AssociationInfo> associationInfos = associationService.selectPop(count);
        return ResultUtil.success(associationInfos);
    }

    /**
     * 获取所有的社团信息
     * @param category
     * @return
     */
    @GetMapping("/list")
    public Result selectByCategory(@RequestParam("category") String category){
        List<AssociationInfo> associationInfos = associationService.selectByCategory(category);
        return ResultUtil.success(associationInfos);
    }

    /**
     * 获取所有的社团
     * @return
     */
    @GetMapping("/all/name")
    public Result getAssociationNames(){
        List<AssociationInfo> associationInfos = associationService.getAssociationNames();
        return ResultUtil.success(associationInfos);
    }

}
