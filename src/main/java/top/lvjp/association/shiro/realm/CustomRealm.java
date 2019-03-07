package top.lvjp.association.shiro.realm;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import top.lvjp.association.entity.User;
import top.lvjp.association.enums.UserTypeEnum;
import top.lvjp.association.mapper.UserMapper;
import top.lvjp.association.shiro.filter.KickFilter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    /**
     * 当前在线用户, 用于判断用户是否重复登录
     */
    private Map<String, Session> activeUser = new ConcurrentHashMap<>();

    /**
     * 获取授权信息
     * @param principals
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String role;
        if (user.getUserType().equals(UserTypeEnum.ROOT.getValue())){
            role = "root";
        } else if (user.getUserType().equals(UserTypeEnum.ADMIN.getValue())){
            role = "admin";
        } else role = "member";
        authorizationInfo.addRole(role);
        System.out.println("role: " + role);
        return authorizationInfo;
    }

    /**
     * 获取身份认证信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String name = (String) token.getPrincipal();
        User user = userMapper.getByName(name);
        if (user == null){
            return null;
        }
        if (activeUser.containsKey(name)){
            Session session = activeUser.get(name);
            try {
                session.setAttribute(KickFilter.IS_KICK, Boolean.TRUE);
            } catch (InvalidSessionException e) {
                log.warn("session 过期, 将其从 activeUser 中移除");
                activeUser.remove(name);
            }
            log.warn("用户 {} 异地登录, 将之前用户踢出", name);
        }
        Subject subject = SecurityUtils.getSubject();
        activeUser.put(name, subject.getSession());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,
                user.getUserPassword(),ByteSource.Util.bytes(user.getSalt()), getName());
        return authenticationInfo;
    }
}