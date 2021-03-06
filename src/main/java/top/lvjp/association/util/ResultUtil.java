package top.lvjp.association.util;

import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.VO.Result;

public class ResultUtil {

    public static Result success(Object object){
        Result<Object> result = new Result<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());
        result.setData(object);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(ResultEnum resultEnum){
        Result<Object> result = new Result<>();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        result.setData(null);
        return result;
    }

    public static  Result error(Integer code, String message) {
        Result<Object> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(null);
        return result;
    }

    public static Result validError(String message){
        Result<Object> result = new Result<>();
        result.setCode(ResultEnum.FORM_VALID_ERROR.getCode());
        result.setMessage(message);
        result.setData(null);
        return result;
    }
}
