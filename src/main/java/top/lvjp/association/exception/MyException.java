package top.lvjp.association.exception;

import top.lvjp.association.enums.ResultEnum;

public class MyException extends RuntimeException{

    private Integer code;

    public MyException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public MyException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
