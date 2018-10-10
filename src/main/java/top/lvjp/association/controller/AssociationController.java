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

    @GetMapping("/detail")
    public Result selectById(@RequestParam("id") Integer id){
        AssociationVO associationVO = associationService.selectById(id);
        if (associationVO == null) {
            return ResultUtil.error(ResultEnum.RESULT_IS_NULL);
        }
        return ResultUtil.success(associationVO);
    }

    @GetMapping("/list/pop")
    public Result selectPop(@RequestParam("count") Integer count){
        if(count == null || count == 0){
            count = 10;
        }
        List<AssociationInfo> associationInfos = associationService.selectPop(count);
        return ResultUtil.success(associationInfos);
    }

    @GetMapping("/category")
    public Result selectByCategory(@RequestParam("category") String category){
        List<AssociationInfo> associationInfos = associationService.selectByCategory(category);
        return ResultUtil.success(associationInfos);
    }
}
