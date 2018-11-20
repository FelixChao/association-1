package top.lvjp.association.controller.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.lvjp.association.VO.NewsInfo;
import top.lvjp.association.VO.Result;
import top.lvjp.association.constant.SessionConstant;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.form.NewsForm;
import top.lvjp.association.service.NewsService;
import top.lvjp.association.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/manage/news")
public class ManageNewsController {

    @Autowired
    private NewsService newsService;

    /**
     * 获取新闻的表单信息
     * @param id 新闻的编号
     * @return newsform
     */
    @GetMapping("/form/{id}")
    public Result getFormById(@PathVariable("id") Integer id){
        return ResultUtil.success(newsService.getFormById(id));
    }

    /**
     * 查询社团文章状态为 status 的新闻
     * @param status 文章发布状态
     * @param pageNum 请求显示的页数
     * @param size 每页大小
     * @param request
     * @return PageVO&lt;NewsInof&gt;
     */
    @GetMapping("/list")
    public Result<NewsInfo> selectByStatus(@RequestParam("status") Integer status,
                                           @RequestParam("pageNum") Integer pageNum,
                                           @RequestParam("size") Integer size,HttpServletRequest request){
        Integer id = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        return ResultUtil.success(newsService.selectByStatus(status,id,pageNum,size));
    }

    /**
     * 通过关键字 key 查询社团的新闻
     * @param key 查询关键字
     * @return PageVO&lt;NewsInfo&gt;
     */
    @GetMapping("/query")
    public Result<NewsInfo> queryByKey(@RequestParam("key") String key,
                                        @RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("size") Integer size, HttpServletRequest request){
        Integer associationId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        return  ResultUtil.success(newsService.queryByKey(key,associationId,pageNum,size));
    }

    /**
     * 发布新闻
     * @param id 新闻编号
     * @return 成功返回1,失败0
     */
    @PostMapping("/publish")
    public Result publish(@RequestParam("id") Integer id,HttpServletRequest request){
        Integer associationId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        int success = newsService.publish(id,associationId);
        if (success == 1){
            return ResultUtil.success();
        }
        return ResultUtil.error(ResultEnum.OPERATE_IS_FAIL);
    }

    /**
     * 更新新闻
     * @param newsForm 新闻表单
     * @return 成功返回1,失败0
     */
    @PostMapping("/update")
    public Result update(@Valid NewsForm newsForm, BindingResult bindingResult,HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(ResultEnum.FORM_VALID_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        Integer associationId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        if (associationId != 0){
            newsForm.setAssociationId(associationId);
        }
        int success = newsService.update(newsForm);
        if (success == 1) return ResultUtil.success();
        return ResultUtil.error(ResultEnum.OPERATE_IS_FAIL);
    }

    /**
     * 填写新闻
     * 如果用户不是超级管理员,只能发布本社团的活动
     * @param newsForm 新闻表单
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@Valid NewsForm newsForm,BindingResult bindingResult,HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(ResultEnum.FORM_VALID_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        Integer associationId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        if (associationId != 0){
            newsForm.setAssociationId(associationId);
        }
        int success = newsService.insert(newsForm);
        if (success == 1) return ResultUtil.success();
        return ResultUtil.error(ResultEnum.OPERATE_IS_FAIL);
    }

    /**
     * 删除新闻
     * @param id 新闻编号
     * @return 成功返回1,失败0
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") Integer id,HttpServletRequest request){
        Integer associationId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        int success = newsService.delete(id,associationId);
        if (success != 0) return ResultUtil.success();
        return ResultUtil.error(ResultEnum.OPERATE_IS_FAIL);
    }

}
