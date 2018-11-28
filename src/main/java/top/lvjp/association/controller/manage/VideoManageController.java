package top.lvjp.association.controller.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.lvjp.association.VO.Result;
import top.lvjp.association.constant.SessionConstant;
import top.lvjp.association.entity.Video;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.form.VideoForm;
import top.lvjp.association.service.VideoService;
import top.lvjp.association.util.FileUtil;
import top.lvjp.association.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/manage/video")
public class VideoManageController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private FileUtil fileUtil;

    /**
     * 查询当前用户所在社团的视频
     * @param pageNum
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/list")
    public Result selectByAssociation(@RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size,
                                      HttpServletRequest request){
        Integer associationId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        return ResultUtil.success(videoService.listByAssociation(associationId,pageNum,size));
    }

    /**
     * 上传视频
     * @param videoForm
     * @param bindingResult
     * @param request
     * @return
     */
    @PostMapping("/upload")
    public Result upload(VideoForm videoForm, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()){
            throw new MyException(ResultEnum.FORM_VALID_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        Video video = new Video();
        Integer associationId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        Integer userId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ID);
        if (associationId == null || userId == null) {
            throw new MyException(ResultEnum.IDENTIFY_VALID_FAILED);
        }
        video.setAssociationId(associationId);
        video.setUserId(userId);
        video.setVideoTitle(videoForm.getTitle());
        video.setVideoDescription(videoForm.getDesc());
        video.setVideoPath(fileUtil.uploadFile(videoForm.getFile(),userId,FileUtil.VIDEO_FILE));
        int count = videoService.insert(video);
        return ResultUtil.success(count);
    }



    /**
     * 更新
     * @param videoForm
     * @param bindingResult
     * @param request
     * @return
     */
    @PostMapping("/update")
    public Result update(VideoForm videoForm, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()){
            throw new MyException(ResultEnum.FORM_VALID_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        Integer associationId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        Video video = new Video();
        video.setVideoId(videoForm.getVideoId());
        video.setVideoTitle(videoForm.getTitle());
        video.setVideoDescription(videoForm.getDesc());
        video.setAssociationId(associationId);
        int count = videoService.update(video);
        if (count == 0) {
            throw new MyException(ResultEnum.OPERATE_IS_FAIL);
        }
        return ResultUtil.success(count);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") Integer videoId, HttpServletRequest request){
        Integer associationId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        int count = videoService.delete(videoId,associationId);
        if (count == 0) {
            throw new MyException(ResultEnum.OPERATE_IS_FAIL);
        }
        return ResultUtil.success(count);
    }
}
