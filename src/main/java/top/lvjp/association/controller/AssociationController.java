package top.lvjp.association.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/index")
    public String say(){
        return "hello";
    }

    @GetMapping("/detail")
    public Result getDetail(@RequestParam("id") Integer id){
        AssociationVO associationVO = associationService.getVOById(id);
        if (associationVO == null) {
            return ResultUtil.error(ResultEnum.RESULT_IS_NULL);
        }
        return ResultUtil.success(associationVO);
    }

    @GetMapping("/pop")
    public Result getPop(@RequestParam("count") Integer count){
        List<AssociationInfo> associationInfos = associationService.selectPop(count);
        return ResultUtil.success(associationInfos);
    }

    @GetMapping("/list")
    public Result selectByCategory(@RequestParam("category") String category){
        List<AssociationInfo> associationInfos = associationService.selectByCategory(category);
        return ResultUtil.success(associationInfos);
    }

}
