package top.lvjp.association.enums;

import lombok.Getter;

@Getter
public enum UserTypeEnum {

    ROOT(1),
    ADMIN(2),
    MEMBER(3);

    private Integer value;

    UserTypeEnum(Integer value) {
        this.value = value;
    }
}
