package top.lvjp.association.enums;

import lombok.Getter;

@Getter
public enum CategoryEnum {

    LILUN(1,"理论实践类"),
    TIYU(2,"体育竞技类"),
    YIHSU(3,"艺术欣赏类"),
    ;

    private Integer code;

    private String category;

    CategoryEnum(Integer code, String category) {
        this.code = code;
        this.category = category;
    }
}
