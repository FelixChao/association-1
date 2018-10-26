package top.lvjp.association.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.lvjp.association.VO.Result;
import top.lvjp.association.entity.User;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.service.impl.UserServiceImpl;
import top.lvjp.association.util.ResultUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;


@Controller
@RequestMapping(value = "/manage")
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 返回验证码
     * @param request
     * @param response
     */
    @GetMapping(value = "/verifyCode")
    public void getValidateCode(HttpServletRequest request,HttpServletResponse response){
        byte[] captchaChallengeAsJpeg = null;
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        String code = defaultKaptcha.createText();
        System.out.println(code);
        request.getSession().setAttribute("rightCode",code);
        BufferedImage bufferedImage = defaultKaptcha.createImage(code);
        try {
            ImageIO.write(bufferedImage,"jpg",byteOutputStream);
        } catch (IOException e) {
            System.out.println("验证码图片输出错误!");
        }
        captchaChallengeAsJpeg = byteOutputStream.getBytes();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        try {
            ServletOutputStream servletOutputStream = response.getOutputStream();
            servletOutputStream.write(captchaChallengeAsJpeg);
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException e) {
            //TODO 处理验证码失败
            System.out.println("验证码图片返回失败");
        }
    }

    @ResponseBody
    @PostMapping(value = "/login")
    public Result login(@RequestParam("name") String name,
                             @RequestParam("password") String password,
                             @RequestParam("code") String code,
                             HttpServletRequest request){
        User user = userService.selectByNameAndPassword(name,password);
        if (user == null) {
            throw new MyException(ResultEnum.LOGIN_INFO_ERROR);
        }
        String rightCode = request.getSession().getAttribute("rightCode").toString();
        if (code.equals(rightCode)){
            HttpSession session = request.getSession();
            session.setAttribute("userName",user.getUserName());
            session.setAttribute("userId",user.getUserId());
            session.setAttribute("userType",user.getUserType());
            return ResultUtil.success(user);
        }
        else throw new MyException(ResultEnum.VALIDATE_CODE_ERROR);
    }
}