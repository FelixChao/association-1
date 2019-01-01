package top.lvjp.association.controller.manage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.Result;
import top.lvjp.association.VO.UserVO;
import top.lvjp.association.entity.User;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.form.UserForm;
import top.lvjp.association.service.UserService;
import top.lvjp.association.util.ResultUtil;

import javax.validation.Valid;

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
    @GetMapping("/list")
    public Result listAll(@RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size){
        PageVO<UserVO> usersPage = userService.listAll(pageNum, size);
        return ResultUtil.success(usersPage);
    }

    /**
     * 通过id查询用户
     * @param id
     * @return user
     */
    @GetMapping("/detail")
    public Result getById(@RequestParam("id") Integer id){
        UserVO userVO = userService.getUserVO(id);
        return ResultUtil.success(userVO);
    }


    /**
     * 添加一个用户
     * @param userForm
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@Valid UserForm userForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(ResultEnum.FORM_VALID_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        int count = userService.insert(userForm);
        return ResultUtil.success(count);
    }

    /**
     * 更新一个用户
     * @param id
     * @param userForm
     * @param bindingResult   表单验证的结果
     * @return
     */
    @PostMapping("/update")
    public Result update(@Valid UserForm userForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(ResultEnum.FORM_VALID_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        int count = userService.update(userForm);
        return ResultUtil.success(count);
    }

    /**
     * 删除一个用户
     * @param id
     * @return
     */
    @DeleteMapping("delete")
    public Result deleteById(@RequestParam("id") Integer id){
        int count = userService.deleteById(id);
        return ResultUtil.success(count);
    }
}
