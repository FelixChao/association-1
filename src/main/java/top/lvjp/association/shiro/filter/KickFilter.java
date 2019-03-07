package top.lvjp.association.shiro.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class KickFilter extends AccessControlFilter {

    public static final String IS_KICK = "session_is_kick";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        if (session.getAttribute(IS_KICK) != null && session.getAttribute(IS_KICK).equals(Boolean.TRUE)){
            session.stop();
            return false;
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.issueRedirect(request, response, "/kicked");
        return false;
    }
}
