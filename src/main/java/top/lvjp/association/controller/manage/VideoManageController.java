package top.lvjp.association.controller.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.Result;
import top.lvjp.association.VO.VideoInfo;
import top.lvjp.association.entity.Video;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.form.VideoForm;
import top.lvjp.association.service.VideoService;
import top.lvjp.association.util.FileUtil;
import top.lvjp.association.util.ResultUtil;
import top.lvjp.association.util.RightsUtil;

import javax.servlet.http.HttpServletRequest;

import static top.lvjp.association.constant.SessionConstant.USER_ASSOCIATION;
import static top.lvjp.association.constant.SessionConstant.USER_ID;
import static top.lvjp.association.constant.SessionConstant.USER_TYPE;

@RestController
@RequestMapping("/manage/video")
public class VideoManageController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private FileUtil fileUtil;

    /**
     * 查询社团的视频
     * 非最高管理员只能查看本社团视频
     * @param pageNum
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/list")
    public Result selectByAssociation(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("size") Integer size,
                                      @RequestParam("associationId") String associationId, HttpServletRequest request){
        String userAssociation = (String) request.getSession().getAttribute(USER_ASSOCIATION);
        Integer userType = (Integer) request.getSession().getAttribute(USER_TYPE);
        if (RightsUtil.hasRights(userAssociation, associationId, userType)){
            PageVO<VideoInfo> videoPageVO = videoService.listByAssociation(associationId,pageNum,size);
            return ResultUtil.success(videoPageVO);
        }
        return ResultUtil.error(ResultEnum.RIGHTS_NOT_SATISFY);
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
        String userAssociation = (String) request.getSession().getAttribute(USER_ASSOCIATION);
        Integer userId = (Integer) request.getSession().getAttribute(USER_ID);
        if (userAssociation == null || userId == null) {
            throw new MyException(ResultEnum.IDENTIFY_VALID_FAILED);
        }
        video.setAssociationId(userAssociation);
        video.setUserId(userId);
        video.setVideoTitle(videoForm.getTitle());
        video.setVideoDescription(videoForm.getDesc());
        video.setVideoPath(fileUtil.uploadFile(videoForm.getFile(), userAssociation, FileUtil.VIDEO_FILE));
        int count = videoService.insert(video);
        return ResultUtil.success(count);
    }


    /**
     * 更新视频信息
     * @param videoId 更新的视频的编号
     * @param title 更新标题
     * @param description 更新描述
     * @param request
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestParam("videoId") Integer videoId, @RequestParam("title") String title ,
                         @RequestParam("desc") String description, HttpServletRequest request){
        String userAssociation = (String) request.getSession().getAttribute(USER_ASSOCIATION);
        Integer userType = (Integer) request.getSession().getAttribute(USER_TYPE);
        if (title == null || title.isEmpty()){
            return ResultUtil.error(ResultEnum.PARAMETERS_IS_ERROR.getCode(), "标题不能为空");
        }
        Video video = new Video();
        video.setVideoId(videoId);
        video.setVideoTitle(title);
        video.setVideoDescription(description);
        int count = videoService.update(video, userAssociation, userType);
        return ResultUtil.success(count);
    }

    /**
     * 删除视频
     * @param videoId
     * @param request
     * @return
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") Integer videoId, HttpServletRequest request){
        String userAssociation = (String) request.getSession().getAttribute(USER_ASSOCIATION);
        Integer userType = (Integer) request.getSession().getAttribute(USER_TYPE);
        int count = videoService.delete(videoId, userAssociation, userType);
        return ResultUtil.success(count);
    }
}
