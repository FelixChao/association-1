package top.lvjp.association.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.ServletRequest;
import java.io.Serializable;

public class MySessionManager extends DefaultWebSessionManager {

    // 优化读取 session, 在第一次读取的时候, 将 session 存储在 request 中, 减少在缓存中 readSession 的次数
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);
        ServletRequest request = null;
        if (sessionKey instanceof WebSessionKey){       // sessionKey 不能直接获取 request
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }
        if (request != null && null != sessionId){
            Object sessionObj = request.getAttribute(sessionId.toString());
            if (sessionObj != null){
                return (Session) sessionObj;
            }
        }
        Session session = super.retrieveSession(sessionKey);
        if (request != null && null != sessionId){
            request.setAttribute(sessionId.toString(), session);
        }
        return session;
    }
}
