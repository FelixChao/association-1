package top.lvjp.association.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.lvjp.association.VO.NewsInfo;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.Result;
import top.lvjp.association.service.NewsService;
import top.lvjp.association.util.ResultUtil;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    /**
     * 查询最近的新闻
     * @param count 数量
     * @return
     */
    @GetMapping("/latest")
    public Result getLatest(@RequestParam("count") Integer count){
        List<NewsInfo> newsInfos = newsService.listLatest(count);
        return ResultUtil.success(newsInfos);

    }

    /**
     * 查询某条新闻的具体内容
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Result getDetail(@RequestParam("id") Integer id){
        NewsInfo newsInfo = newsService.getInfoById(id);
        return ResultUtil.success(newsInfo);
    }

    /**
     * 分页查询所有新闻
     * @param pageNum
     * @param size
     * @return
     */
    @GetMapping("/list")
    public Result listAll(@RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size){
        PageVO<NewsInfo> newsInfoPageVO = newsService.listAll(pageNum, size);
        return ResultUtil.success(newsInfoPageVO);
    }
}
