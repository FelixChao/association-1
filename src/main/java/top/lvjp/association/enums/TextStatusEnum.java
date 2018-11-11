package top.lvjp.association.enums;

import lombok.Getter;

@Getter
public enum TextStatusEnum {

    PUBLISH(1,"发布"),
    UNPUBLISH(0,"草稿箱");

    private Integer Status;

    private String message;

    TextStatusEnum(int status, String message) {
        Status = status;
        this.message = message;
    }
}
