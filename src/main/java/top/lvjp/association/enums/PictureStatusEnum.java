package top.lvjp.association.enums;

import lombok.Getter;

@Getter
public enum PictureStatusEnum {

    IS_ICON(1),
    NOT_ICON(0);

    int status;

    private PictureStatusEnum(int status) {
        this.status = status;
    }


}
