package top.lvjp.association.service;

import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.PictureInfo;
import top.lvjp.association.VO.PictureVO;
import top.lvjp.association.entity.Picture;

import java.util.List;

public interface PictureService {

    /**
     * 查询所有图片
     * @param pageNum
     * @param size
     * @return
     */
    PageVO<PictureInfo> listAll(Integer pageNum, Integer size);

    /**
     * 查询某个社团的图片
     * 非最高管理员只能查询本社团图片
     * @param associationId
     * @param pageNum
     * @param size
     * @return
     */
    PageVO<PictureInfo> listByAssociation(String associationId, Integer pageNum, Integer size);

    /**
     * 查询社徽
     * @param pageNum
     * @param size
     * @return
     */
    PageVO<PictureInfo> listAssociationIcon(Integer pageNum, Integer size);

//    /**
//     * 查询活动的图标
//     * @param pageNum
//     * @param size
//     * @return
//     */
//    PageVO<PictureInfo> listActivityIcon(Integer pageNum, Integer size);

    /**
     * 查询一张图片的具体信息
     * @param id
     * @return
     */
    PictureVO getById(Integer id);

    /**
     * 上传一张图片
     * @param picture
     * @return
     */
    int insert(Picture picture);

    /**
     * 删除一组图片, 只能删除本社团的图片
     * @param pictureIds 要删除的图片 id 数组
     * @param associationId
     * @return 返回成功删除的数量
     */
    int delete(Integer[] pictureIds, String associationId, Integer userType);

    /**
     * 获取头图, 首页滑动图片
     * @return
     */
    List<PictureInfo> listHeadPicture();

    /**
     * 替换头图
     * @param oldId
     * @param newId
     */
    void replaceHeadIcon(Integer oldId, Integer newId);

    /**
     * 更新头图
     * @param pictureId 需要更新的图片 id
     * @param isIcon 更新的状态, 是否作为头图使用
     */
    void updateHeadIcon(Integer pictureId, Integer isIcon);

}
