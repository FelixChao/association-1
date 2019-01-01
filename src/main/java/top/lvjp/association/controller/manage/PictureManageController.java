package top.lvjp.association.controller.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.PictureInfo;
import top.lvjp.association.VO.PictureVO;
import top.lvjp.association.VO.Result;
import top.lvjp.association.constant.SessionConstant;
import top.lvjp.association.entity.Picture;
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

    @GetMapping("/list")
    public Result listAll(@RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size){
        PageVO<PictureInfo> pictureInfo = pictureService.listAll(pageNum, size);
        return ResultUtil.success(pictureInfo);
    }

    @GetMapping("/association")
    public Result listByAssociation(@RequestParam("associationId") String associationId,
                                    @RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size){
        PageVO<PictureInfo> pictureInfo = pictureService.listByAssciation(associationId, pageNum, size);
        return ResultUtil.success(pictureInfo);
    }

    @GetMapping("/icon/association")
    public Result listAssociationIcon(@RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size){
        PageVO<PictureInfo> pictureInfo = pictureService.listAssociationIcon(pageNum, size);
        return ResultUtil.success(pictureInfo);
    }

// TODO 以下接口未经测试

//    @GetMapping("/activity/icon")
//    public Result listActivityIcon(@RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size){
//        PageVO<PictureInfo> pictureInfo = pictureService.listActivityIcon(pageNum, size);
//        return ResultUtil.success(pictureInfo);
//    }

    @GetMapping("/detail/{id}")
    public Result getById(@PathVariable("id") Integer id){
        PictureVO pictureVO = pictureService.getById(id);
        return ResultUtil.success(pictureVO);
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
        Integer userId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ID);
        picture.setUserId(userId);
        String associationId = (String) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        picture.setAssociationId(associationId);
        String path = fileUtil.uploadFile(file, userId, FileUtil.IMAGE_FILE);
        picture.setPicturePath(path);
        int count = pictureService.insert(picture);
        return ResultUtil.success(count);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("pictureIds") Integer[] ids, HttpServletRequest request){
        String userAssociation = (String) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        if (userAssociation.equals(SessionConstant.ROOT_ASSOCIATION_VALUE)){
            userAssociation = null;
        }
        int success = pictureService.delete(ids, userAssociation);
        int fails = ids.length - success;
        return ResultUtil.success("成功删除" + success + "条数据, " + fails + "数据删除失败, 可能权限不足或有图片被作为图标使用");
    }

    // TODO 最高管理员可以强制删除图片
}
