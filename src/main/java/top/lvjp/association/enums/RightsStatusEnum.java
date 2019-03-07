package top.lvjp.association.enums;

import lombok.Getter;

@Getter
public enum RightsStatusEnum {

    UNPROCESSING(0),
    PROCESSING(1),
    RESOLVED(2);

    private Integer value;

    RightsStatusEnum(Integer value) {
        this.value = value;
    }
}
