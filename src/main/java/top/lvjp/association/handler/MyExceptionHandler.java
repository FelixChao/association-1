package top.lvjp.association.handler;

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
            System.out.println(e.getMessage());
            return ResultUtil.error(myException.getCode(),myException.getMessage());
        }
        else {
            e.printStackTrace();
//            return ResultUtil.error(-1,e.getMessage());
            return ResultUtil.error(ResultEnum.UNKNOW_ERROR);
        }
    }
}
