package top.lvjp.association.enums;

import lombok.Getter;

@Getter
public enum PictureIconEnum {

    NOT_ICON(0),
    ASSOCIATION_ICON(1),
    HEAD_ICON(2);

    int value;

    PictureIconEnum(int value) {
        this.value = value;
    }

}
