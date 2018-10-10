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

    @GetMapping("/latest")
    public Result selectLatest(){
        List<VideoInfo> videoInfos = videoService.selectLatest();
        return ResultUtil.success(videoInfos);
    }

    @GetMapping("detail")
    public Result selectById(@RequestParam("id") Integer id){
        VideoInfo videoInfo = videoService.selectById(id);
        return ResultUtil.success(videoInfo);
    }
}
