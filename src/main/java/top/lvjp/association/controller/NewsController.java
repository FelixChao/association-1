package top.lvjp.association.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.lvjp.association.VO.NewsInfo;
import top.lvjp.association.VO.Result;
import top.lvjp.association.service.NewsService;
import top.lvjp.association.util.ResultUtil;

import java.util.List;

@RestController
@RequestMapping("news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/latest")
    public Result selectLatest(@RequestParam("count") Integer count){
        List<NewsInfo> newsInfo = newsService.selectLatest(count);
        return ResultUtil.success(newsInfo);
    }

    @GetMapping("/detail")
    public Result selectById(@RequestParam("id") Integer id){
        NewsInfo newsInfo = newsService.selectById(id);
        return ResultUtil.success(newsInfo);
    }

}
