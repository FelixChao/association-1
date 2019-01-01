package top.lvjp.association.enums;

import lombok.Getter;

@Getter
public enum ApplyStatusEnum {

    ALLOW_APPLY(1),
    FORBID_APPLY(0);

    private Integer status;

    ApplyStatusEnum(Integer status){
        this.status = status;
    }
}
