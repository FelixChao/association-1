package top.lvjp.association.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.lvjp.association.VO.Result;
import top.lvjp.association.VO.VideoInfo;
import top.lvjp.association.service.VideoService;
import top.lvjp.association.util.ResultUtil;

import java.util.List;

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
        List<VideoInfo> videoInfos = videoService.selectLatest(count);
        return ResultUtil.success(videoInfos);
    }

    /**
     * 视频详情
     * @param id
     * @return
     */
    @GetMapping("detail")
    public Result getDetail(@RequestParam("id") Integer id){
        VideoInfo videoInfo = videoService.getInfoById(id);
        return ResultUtil.success(videoInfo);
    }
}
