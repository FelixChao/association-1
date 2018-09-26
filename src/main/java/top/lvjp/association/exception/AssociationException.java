package top.lvjp.association.exception;

import top.lvjp.association.enums.ResultEnum;

public class AssociationException extends RuntimeException{

    private Integer code;

    public AssociationException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public AssociationException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

}
