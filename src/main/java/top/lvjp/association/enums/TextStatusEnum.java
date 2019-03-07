package top.lvjp.association.enums;

import lombok.Getter;

@Getter
public enum TextStatusEnum {

    PUBLISH(1),
    UN_PUBLISH(0);

    private Integer Status;

    TextStatusEnum(int status) {
        Status = status;
    }
}
