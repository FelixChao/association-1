package top.lvjp.association.util;

import top.lvjp.association.enums.UserTypeEnum;

public class RightsUtil {

    /**
     * 判断用户是否拥有操作某社团的权限
     * @param userAssociation 用户所属社团
     * @param associationId 目标社团
     * @param userType  用户类型
     * @return
     */
    public static boolean hasRights(String userAssociation, String associationId, Integer userType){
        return userType.equals(UserTypeEnum.ROOT.getValue())
                || userAssociation.equals(associationId);
    }

//    /**
//     * 通过 shiro 获取当前用户的信息
//     * @return
//     */
//    public static User getCurrentUser(){
//        User user = (User) SecurityUtils.getSubject().getPrincipal();
//        return user;
//    }

//    public static User getCurrentUser(HttpServletRequest request){
//        String userAssociation = (String) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
//        Integer userId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ID);
//        String username = (String) request.getSession().getAttribute(SessionConstant.USER_NAME);
//        Integer userType = (Integer) request.getSession().getAttribute(SessionConstant.USER_TYPE);
//        User user = new User();
//        user.setUserId(userId);
//        user.setUserName(username);
//        user.setUserType(userType);
//        user.setAssociationId(userAssociation);
//        return user;
//    }
}
