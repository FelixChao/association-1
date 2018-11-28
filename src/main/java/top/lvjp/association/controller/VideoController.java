package top.lvjp.association.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.lvjp.association.VO.Result;
import top.lvjp.association.service.VideoService;
import top.lvjp.association.util.ResultUtil;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /**
     * 最近的视频
     * @param count
     * @return
     */
    @GetMapping("/latest")
    public Result selectLatest(@RequestParam("count") Integer count){
        return ResultUtil.success(videoService.listLatest(count));
    }

    /**
     * 全部视频
     * @param pageNum
     * @param size
     * @return
     */
    @GetMapping("/all")
    public Result listAll(@RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size){
        return ResultUtil.success(videoService.listAll(pageNum, size));
    }

    /**
     * 视频详情
     * @param id
     * @return
     */
    @GetMapping("detail")
    public Result getDetail(@RequestParam("id") Integer id){
        return ResultUtil.success(videoService.getInfoById(id));
    }
}
