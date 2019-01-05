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
import top.lvjp.association.enums.PictureIconEnum;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.service.PictureService;
import top.lvjp.association.util.FileUtil;
import top.lvjp.association.util.ResultUtil;
import top.lvjp.association.util.RightsTestUtil;

import javax.servlet.http.HttpServletRequest;

import static top.lvjp.association.constant.SessionConstant.USER_ASSOCIATION;

@RestController
@RequestMapping("/manage/picture")
public class PictureManageController {

    @Autowired
    private PictureService pictureService;

    @Autowired
    private FileUtil fileUtil;

    /**
     * 获取所有图片
     * @param pageNum
     * @param size
     * @return
     */
    @GetMapping("/list")
    public Result listAll(@RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size){
        PageVO<PictureInfo> pictureInfo = pictureService.listAll(pageNum, size);
        return ResultUtil.success(pictureInfo);
    }

    /**
     * 按社团获取图片
     * @param associationId
     * @param pageNum
     * @param size
     * @param request
     * @return
     */
    @GetMapping("/association")
    public Result listByAssociation(@RequestParam("associationId") String associationId,
                                    @RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size,
                                    HttpServletRequest request){
        String userAssociation = (String) request.getSession().getAttribute(SessionConstant.USER_ASSOCIATION);
        if (RightsTestUtil.hasRights(userAssociation, associationId)){
            return ResultUtil.error(ResultEnum.RIGHTS_NOT_SATISFY);
        }
        PageVO<PictureInfo> pictureInfo = pictureService.listByAssociation(associationId, pageNum, size);
        return ResultUtil.success(pictureInfo);
    }

    /**
     * 获取所有社徽
     * @param pageNum
     * @param size
     * @return
     */
    @GetMapping("/icon/association")
    public Result listAssociationIcon(@RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size){
        PageVO<PictureInfo> pictureInfo = pictureService.listAssociationIcon(pageNum, size);
        return ResultUtil.success(pictureInfo);
    }

    /**
     * 获取图片的详细信息
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Result getById(@RequestParam("id") Integer id){
        PictureVO pictureVO = pictureService.getById(id);
        return ResultUtil.success(pictureVO);
    }

    /**
     * 上传图片
     * @param file 图片文件
     * @param title 图片标题
     * @param request
     * @return
     */
    @PostMapping("/upload")
    public Result upload(@RequestParam("file")MultipartFile file, @RequestParam("title") String title,
                         HttpServletRequest request){
        Picture picture = new Picture();
        if (title != null) {
            picture.setPictureTitle(title);
        }
        Integer userId = (Integer) request.getSession().getAttribute(SessionConstant.USER_ID);
        picture.setUserId(userId);
        String associationId = (String) request.getSession().getAttribute(USER_ASSOCIATION);
        picture.setAssociationId(associationId);
        String path = fileUtil.uploadFile(file, associationId, FileUtil.IMAGE_FILE);
        picture.setPicturePath(path);
        int count = pictureService.insert(picture);
        return ResultUtil.success(count);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("ids") Integer[] ids, HttpServletRequest request){
        String userAssociation = (String) request.getSession().getAttribute(USER_ASSOCIATION);
        int success = pictureService.delete(ids, userAssociation);
        int fails = ids.length - success;
        return ResultUtil.success("成功删除" + success + "条数据, " + fails + "数据删除失败, 可能权限不足或有图片被作为社徽使用");
    }

    @PostMapping("/head/replace")
    public Result updateHead(@RequestParam("oldId") Integer oldId, @RequestParam("newId") Integer newId){
        pictureService.replaceHeadIcon(oldId, newId);
        return ResultUtil.success();
    }

    @PostMapping("/head/add")
    public Result addHead(@RequestParam("id") Integer id){
        pictureService.updateHeadIcon(id, PictureIconEnum.HEAD_ICON.getValue());
        return ResultUtil.success();
    }

    @DeleteMapping("/head/delete")
    public Result delete(@RequestParam("id") Integer id){
        pictureService.updateHeadIcon(id, PictureIconEnum.NOT_ICON.getValue());
        return ResultUtil.success();
    }

}
