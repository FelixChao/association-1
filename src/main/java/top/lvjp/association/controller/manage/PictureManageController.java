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
import top.lvjp.association.util.RightsUtil;

import javax.servlet.http.HttpServletRequest;

import static top.lvjp.association.constant.SessionConstant.USER_ASSOCIATION;
import static top.lvjp.association.constant.SessionConstant.USER_TYPE;

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
        Integer userType = (Integer) request.getSession().getAttribute(SessionConstant.USER_TYPE);
        if (RightsUtil.hasRights(userAssociation, associationId, userType)){
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
        String associationId = (String) request.getSession().getAttribute(USER_ASSOCIATION);
        picture.setUserId(userId);
        picture.setAssociationId(associationId);
        String path = fileUtil.uploadFile(file, associationId, FileUtil.IMAGE_FILE);
        picture.setPicturePath(path);
        int count = pictureService.insert(picture);
        return ResultUtil.success(count);
    }

    /**
     * 删除一组图片
     * @param ids
     * @param request
     * @return
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestParam("ids") Integer[] ids, HttpServletRequest request){
        String userAssociation = (String) request.getSession().getAttribute(USER_ASSOCIATION);
        Integer userType = (Integer) request.getSession().getAttribute(USER_TYPE);
        int success = pictureService.delete(ids, userAssociation, userType);
        int fails = ids.length - success;
        String message = fails > 0 ? ", 可能权限不足或有图片被作为社徽使用!" : "";
        return ResultUtil.success("成功删除" + success + "张图片, " + fails + "张删除失败" + message);
    }

    /**
     * 替换头图, 首页顶层展示的图片
     * 需最高管理员权限
     * @param oldId
     * @param newId
     * @return
     */
    @PostMapping("/head/replace")
    public Result updateHead(@RequestParam("oldId") Integer oldId, @RequestParam("newId") Integer newId){
        pictureService.replaceHeadIcon(oldId, newId);
        return ResultUtil.success();
    }

    /**
     * 添加头图
     * 最高管理员权限
     * @param id
     * @return
     */
    @PostMapping("/head/add")
    public Result addHead(@RequestParam("id") Integer id){
        pictureService.updateHeadIcon(id, PictureIconEnum.HEAD_ICON.getValue());
        return ResultUtil.success();
    }

    /**
     * 删除头图
     * 最高管理员权限
     * @param id
     * @return
     */
    @DeleteMapping("/head/delete")
    public Result deleteHead(@RequestParam("id") Integer id){
        pictureService.updateHeadIcon(id, PictureIconEnum.NOT_ICON.getValue());
        return ResultUtil.success();
    }

}
