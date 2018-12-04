package top.lvjp.association.constant;

public class RegexpConstant {

    public final static String STUDENT_NUMBER = "^20[12]\\d{7}$";

    public final static String PHONE = "^1[3456789]\\d{9}$";

    public final static String QQ = "\\d{5,12}";

    public final static String LABEL = "^(?:[\\u4e00-\\u9fa5]+[,，])*[\\u4e00-\\u9fa5]+$";

    public static final String EMAIL = "^[\\u4e00-\\u9fa5a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(?:\\.[a-zA-Z0-9_-]+)+$";
}
