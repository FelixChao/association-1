package top.lvjp.association.controller.manage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping(value = "/manage")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 获取验证码
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getVerify")
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
    public Result login(@RequestParam("name") String name,
                            @RequestParam("password") String password,
                            @RequestParam("code") String code, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        String rightCode = session.getAttribute(RandomValidateCodeUtil.CODE_KEY).toString();
        if (!rightCode.equals(code)) {
            return ResultUtil.error(ResultEnum.VALIDATE_CODE_ERROR);
        }
        User user = userService.selectByNameAndPassword(name,password);
        if (user == null) {
            return ResultUtil.error(ResultEnum.LOGIN_INFO_ERROR);
        }
        session.setAttribute(SessionConstant.USER_NAME,user.getUserName());
        session.setAttribute(SessionConstant.USER_ID,user.getUserId());
        session.setAttribute(SessionConstant.USER_TYPE,user.getUserType());
        session.setAttribute(SessionConstant.USER_ASSOCIATION,user.getAssociationId());
        UserVO userVO = userService.getUserVO(user.getUserId());
        log.info("用户 id: {} 于 {} 登录, 基本信息为 {}", user.getUserId(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()),
                user.toString());
        return ResultUtil.success(userVO);
    }

    @PostMapping(value = "/logout")
    public Result logout( HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        session.setMaxInactiveInterval(0);
//        log.info("用户 id: {} 于 {} 退出登录, 基本信息为 {}", user.getUserId(),
//                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()),
//                user.toString());
        return ResultUtil.success();
    }

}