package top.lvjp.association.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lvjp.association.VO.PictureInfo;
import top.lvjp.association.VO.Result;
import top.lvjp.association.service.PictureService;
import top.lvjp.association.util.ResultUtil;

import java.util.List;

@RestController
@RequestMapping("/picture")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @GetMapping("/head")
    public Result listHeadPicture(){
        List<PictureInfo> pictureInfo = pictureService.listHeadPicture();
        return ResultUtil.success(pictureInfo);
    }
}
