package top.lvjp.association.service;

import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.PictureVO;
import top.lvjp.association.entity.Picture;

public interface PictureService {

    /**
     * 查询所有图片
     * @param pageNum
     * @param size
     * @return
     */
    PageVO<PictureVO> listAll(Integer pageNum, Integer size);

    /**
     * 查询某个社团的图片
     * @param associationId
     * @param pageNum
     * @param size
     * @return
     */
    PageVO<PictureVO> listByAssciation(Integer associationId, Integer pageNum, Integer size);

    /**
     * 查询社徽
     * @param pageNum
     * @param size
     * @return
     */
    PageVO<PictureVO> listAssociationIcon(Integer pageNum, Integer size);

    /**
     * 查询活动的图标
     * @param pageNum
     * @param size
     * @return
     */
    PageVO<PictureVO> listActivityIcon(Integer pageNum, Integer size);

    /**
     * 查询一张图片的具体信息
     * @param id
     * @return
     */
    Picture getById(Integer id);

    /**
     * 上传一张图片
     * @param picture
     * @return
     */
    int insert(Picture picture);

    /**
     * 更新社徽
     * @param oldPicture
     * @param newPicture
     * @param associationId
     * @return
     */
    int updateAssociationIcon(Integer oldPicture, Integer newPicture, Integer associationId);

    int updateActivityIcon(Integer oldPicture, Integer newPicture, Integer associationId, Integer activityId);

    /**
     * 删除一组图片, 只能删除本社团的图片
     * @param pictureIds 要删除的图片 id 数组
     * @param associationId
     * @return
     */
    int delete(Integer[] pictureIds, Integer associationId);

}
