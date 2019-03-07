package top.lvjp.association.handler;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.lvjp.association.VO.Result;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.util.ResultUtil;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof MyException){
            MyException myException = (MyException)e;
            return ResultUtil.error(myException.getCode(),myException.getMessage());
        }
        else {
            e.printStackTrace();
            return ResultUtil.error(-1,e.getMessage());
//            return ResultUtil.error(ResultEnum.UNKNOW_ERROR);
        }
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public Result handleUnauthorized(UnauthorizedException e){
        return ResultUtil.error(ResultEnum.RIGHTS_NOT_SATISFY);
    }

    @ExceptionHandler(value = UnauthenticatedException.class)
    public String handleUnauthorized(UnauthenticatedException e){
        return "redirect:/login.html";
    }

}
