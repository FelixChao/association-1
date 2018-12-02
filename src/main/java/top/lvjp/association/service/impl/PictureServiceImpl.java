package top.lvjp.association.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lvjp.association.VO.PictureVO;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.entity.Picture;
import top.lvjp.association.enums.PictureStatusEnum;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.mapper.ActivityMapper;
import top.lvjp.association.mapper.AssociationMapper;
import top.lvjp.association.mapper.PictureMapper;
import top.lvjp.association.service.PictureService;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private AssociationMapper associationMapper;

    @Autowired
    private ActivityMapper activityMapper;


    @Override
    public PageVO<PictureVO> listAll(Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum, size);
        List<PictureVO> pictureList = pictureMapper.listByAssociation(null);
        PageInfo<PictureVO> picturePageInfo = new PageInfo<>(pictureList);
        return new PageVO<>(picturePageInfo);
    }

    @Override
    public PageVO<PictureVO> listByAssciation(Integer associationId, Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum, size);
        List<PictureVO> pictureList = pictureMapper.listByAssociation(associationId);
        PageInfo<PictureVO> picturePageInfo = new PageInfo<>(pictureList);
        return new PageVO<>(picturePageInfo);
    }

    @Override
    public PageVO<PictureVO> listAssociationIcon(Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum, size);
        List<PictureVO> pictureVOS = associationMapper.listAssociationIcon();
        PageInfo<PictureVO> iconPageInfo = new PageInfo<>(pictureVOS);
        return new PageVO<PictureVO>(iconPageInfo);
    }

    @Override
    public PageVO<PictureVO> listActivityIcon(Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum, size);
        List<PictureVO> pictureVOS = activityMapper.listActivityIcon();
        PageInfo<PictureVO> iconPageInfo = new PageInfo<>(pictureVOS);
        return new PageVO<PictureVO>(iconPageInfo);
    }

    @Override
    public Picture getById(Integer id) {
        return pictureMapper.getById(id);
    }

    @Override
    public int insert(Picture picture) {
        return pictureMapper.insert(picture);
    }

    @Override
    @Transactional
    public synchronized int updateAssociationIcon(Integer oldPicture, Integer newPicture, Integer associationId) {
        if (oldPicture != null) {
            pictureMapper.updateIcon(oldPicture, associationId, PictureStatusEnum.NOT_ICON.getStatus());
        }
        pictureMapper.updateIcon(newPicture, associationId, PictureStatusEnum.IS_ICON.getStatus());
        String path = pictureMapper.getById(newPicture).getPicturePath();
        return associationMapper.updateAssociationIcon(newPicture, path, associationId);
    }

    @Override
    @Transactional
    public synchronized int updateActivityIcon(Integer oldPicture, Integer newPicture, Integer associationId,
                                  Integer activityId) {
        if (oldPicture != null) {
            pictureMapper.updateIcon(oldPicture, associationId, PictureStatusEnum.NOT_ICON.getStatus());
        }
        pictureMapper.updateIcon(newPicture, associationId, PictureStatusEnum.IS_ICON.getStatus());
        String path = pictureMapper.getById(newPicture).getPicturePath();
        return activityMapper.updateActivityIcon(newPicture, path, activityId);
    }

    @Override
    @Transactional
    public int delete(Integer[] pictureIds, Integer associationId) {
        int length = pictureIds.length;
        int count = pictureMapper.delete(pictureIds, associationId);
        if (length != count){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY);
        }
        return count;
    }
}
