package top.lvjp.association.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lvjp.association.VO.Result;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.util.ResultUtil;

@RestController
public class ErrorController {

    @GetMapping("unautho")
    public Result unautho(){
        return ResultUtil.error(ResultEnum.RIGHTS_NOT_SATISFY);
    }

    @GetMapping("kicked")
    public Result kicked(){
        return ResultUtil.error(ResultEnum.ACCOUNT_IS_KICKED);
    }
}
