package top.lvjp.association.controller.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.lvjp.association.VO.Result;
import top.lvjp.association.constant.SessionConstant;
import top.lvjp.association.entity.Picture;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.service.PictureService;
import top.lvjp.association.util.FileUtil;
import top.lvjp.association.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/manage/picture")
public class PictureManageController {

    @Autowired
    private PictureService pictureService;

    @Autowired
    private FileUtil fileUtil;

    @GetMapping("/all")
    public Result listAll(@RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size){
        return ResultUtil.success(pictureService.listAll(pageNum, size));
    }

    @GetMapping("/{associationId}")
    public Result listByAssociation(@PathVariable("associationId") Integer associationId,
                                    @RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size){
        return ResultUtil.success(pictureService.listByAssciation(associationId, pageNum, size));
    }

    @GetMapping("/association/icon")
    public Result listAssociationIcon(@RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size){
        return ResultUtil.success(pictureService.listAssociationIcon(pageNum, size));
    }

    @GetMapping("/activity/icon")
    public Result listActivityIcon(@RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size){
        return ResultUtil.success(pictureService.listActivityIcon(pageNum, size));
    }

    @GetMapping("/detail/{id}")
    public Result getById(@PathVariable("id") Integer id){
        return ResultUtil.success(pictureService.getById(id));
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam("file")MultipartFile file, @RequestParam("title") String title,
                         @RequestParam("category") Integer category, HttpServletRequest request){
        Picture picture = new Picture();
        if (title != null) {
            picture.setPictureTitle(title);
        }
        if (category == null) {
            picture.setPictureCategory(category);
        }
        Integer userId = (Integer)  request.getSession().getAttribute(SessionConstant.USER_ID);
        picture.setUserId(userId);
        Integer associationId = (Integer)  request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        picture.setAssociationId(associationId);
        String path = fileUtil.uploadFile(file, userId, FileUtil.IMAGE_FILE);
        picture.setPicturePath(path);
        int count = pictureService.insert(picture);
        if (count != 0) return ResultUtil.success(count);
        return ResultUtil.error(ResultEnum.OPERATE_IS_FAIL);
    }

    @PostMapping("/association/icon/update")
    public Result updateAssociationIcon(@RequestParam(value = "oldPicture", required = false ) Integer oldPicture,
                                        @RequestParam("newPicture") Integer newPicture,
                                        @RequestParam("associationId") Integer associationId){
        int count = pictureService.updateAssociationIcon(oldPicture, newPicture, associationId);
        if (count != 0) return ResultUtil.success(count);
        return ResultUtil.error(ResultEnum.OPERATE_IS_FAIL);
    }

    @PostMapping("/activity/icon/update")
    public Result updateActivityIcon(@RequestParam(value = "oldPicture", required = false ) Integer oldPicture,
                                     @RequestParam("newPicture") Integer newPicture,
                                     @RequestParam("activityId") Integer activityId, HttpServletRequest request){
        Integer associationId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        int count = pictureService.updateActivityIcon(oldPicture, newPicture, associationId, activityId);
        if (count != 0) return ResultUtil.success(count);
        return ResultUtil.error(ResultEnum.OPERATE_IS_FAIL);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("pictureIds") Integer[] ids, HttpServletRequest request){
        Integer associationId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        int count = pictureService.delete(ids, associationId);
        return ResultUtil.success(count);
    }
}
