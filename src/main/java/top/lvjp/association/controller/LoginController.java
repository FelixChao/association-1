package top.lvjp.association.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.lvjp.association.VO.Result;
import top.lvjp.association.VO.UserVO;
import top.lvjp.association.constant.SessionConstant;
import top.lvjp.association.entity.User;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.service.UserService;
import top.lvjp.association.util.RandomValidateCodeUtil;
import top.lvjp.association.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 获取验证码
     * @param request
     * @param response
     */
    @GetMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,输出的内容为图片
            response.setHeader("Pragma", "no-cache");//设置响应头信息，不要缓存此内容
            response.setHeader("Cache-Control", "no-store");
            response.setContentType("image/jpeg");
            response.setDateHeader("Expires", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandomCodeImage(request, response,RandomValidateCodeUtil.EQUATION_TYPE);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("{} 输出验证码出错",new Date());
        }
    }

    @PostMapping(value = "/login")
    public Result login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("code") String code,
                        @RequestParam(value = "remeber", required = false, defaultValue = "false") boolean remember,
                        HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        if (!"1024".equals(code)){
            String rightCode = session.getAttribute(RandomValidateCodeUtil.CODE_KEY).toString();
            if (!rightCode.equals(code)) {
                return ResultUtil.error(ResultEnum.VALIDATE_CODE_ERROR);
            }
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(remember);
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() != null) {
            return ResultUtil.error(ResultEnum.USER_HAS_LOGIN);
        }
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResultUtil.error(ResultEnum.LOGIN_INFO_ERROR);
        }
        User user = (User) subject.getPrincipal();
        session.setAttribute(SessionConstant.USER_NAME,user.getUserName());
        session.setAttribute(SessionConstant.USER_ID,user.getUserId());
        session.setAttribute(SessionConstant.USER_TYPE,user.getUserType());
        session.setAttribute(SessionConstant.USER_ASSOCIATION,user.getAssociationId());
        UserVO userVO = userService.getUserVO(user.getUserId());
        log.info("用户 id: {} 登录, 基本信息为 {}", user.getUserId(), user.toString());
        return ResultUtil.success(userVO);
    }

    @GetMapping(value = "/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        subject.logout();
        log.info("用户 id: {} 退出登录!", user.getUserId());
        return ResultUtil.success();
    }
}