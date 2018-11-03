package top.lvjp.association.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.lvjp.association.VO.Result;
import top.lvjp.association.entity.User;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.form.UserForm;
import top.lvjp.association.service.UserService;
import top.lvjp.association.util.ResultUtil;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/manage/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     * @return List &lt User &gt
     */
    @GetMapping("/all")
    public Result getAll(@RequestParam("page") int page){
        PageInfo<User> usersPage = userService.selectAll(page,10);
        List<User> users = usersPage.getList();
        return ResultUtil.success(users);
    }

    /**
     * 通过id查询用户
     * @param id
     * @return user
     */
    @GetMapping("/{id}")
    public Result selectById(@PathVariable("id") Integer id){
        User user = userService.selectById(id);
        if(user == null){
            return ResultUtil.error(ResultEnum.USER_NOT_EXISTS);
        }
        return ResultUtil.success(user);
    }


    /**
     * 添加一个用户
     * @param userForm
     * @return
     */
    @PostMapping("/add")
    public Result insert(@Valid UserForm userForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(ResultEnum.FORM_VALID_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        userService.insert(userForm);
        return ResultUtil.success();
    }

    /**
     * 更新一个用户
     * @param id
     * @param userForm
     * @param bindingResult   表单验证的结果
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestParam("id") Integer id ,@Valid UserForm userForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(ResultEnum.FORM_VALID_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        User user =new User();
        BeanUtils.copyProperties(userForm,user);
        user.setUserId(id);
        int success =  userService.update(user);
        if(success == 1){
            return ResultUtil.success();
        }
        return ResultUtil.error(ResultEnum.OPERATE_IS_FAIL);
    }

    /**
     * 删除一个用户
     * @param id
     * @return
     */
    @PostMapping("del")
    public Result deleteById(@RequestParam("id") Integer id){
        int success =  userService.deleteById(id);
        if(success == 1){
            return ResultUtil.success();
        }
        return ResultUtil.error(ResultEnum.OPERATE_IS_FAIL);
    }
}
