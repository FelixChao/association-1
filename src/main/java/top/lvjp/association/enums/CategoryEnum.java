package top.lvjp.association.enums;

public enum CategoryEnum {

    YIHSU(1,"艺术类"),
    TIYU(2,"体育类"),
    WENXUE(3,"文学类"),
    ;

    private Integer code;

    private String category;

    CategoryEnum(Integer code, String category) {
        this.code = code;
        this.category = category;
    }

    public Integer getCode() {
        return code;
    }

    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }
}
