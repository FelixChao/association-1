package top.lvjp.association.util;

import top.lvjp.association.constant.SessionConstant;

public class RightsTestUtil {

    public static boolean hasRights(String userAssociation, String associationId){
        return userAssociation.equals(SessionConstant.ROOT_ASSOCIATION_VALUE)
                || userAssociation.equals(associationId);
    }
}
