package top.lvjp.association.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lvjp.association.VO.ActivityInfo;
import top.lvjp.association.VO.AssociationInfo;
import top.lvjp.association.VO.AssociationVO;
import top.lvjp.association.VO.NewsInfo;
import top.lvjp.association.dto.ActivitiesDTO;
import top.lvjp.association.entity.Association;
import top.lvjp.association.entity.Picture;
import top.lvjp.association.enums.PictureIconEnum;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.form.AssociationForm;
import top.lvjp.association.mapper.ActivityMapper;
import top.lvjp.association.mapper.AssociationMapper;
import top.lvjp.association.mapper.NewsMapper;
import top.lvjp.association.mapper.PictureMapper;
import top.lvjp.association.service.AssociationService;

import java.util.List;

@Service
public class AssociationServiceImpl implements AssociationService {

    @Autowired
    private AssociationMapper associationMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public AssociationVO getVOById(String id) {
        Association association = associationMapper.getById(id);
        List<NewsInfo> newsInfos = newsMapper.listByAssociation(id);
        List<ActivityInfo> current = activityMapper.selectCurrentByAssociation(id);
        List<ActivityInfo> future = activityMapper.selectFutureByAssociation(id);
        List<ActivityInfo> past = activityMapper.selectPastByAssociation(id);
        ActivitiesDTO activitiesDTO = new ActivitiesDTO(current,future,past);
        AssociationVO associationVO = new AssociationVO();
        BeanUtils.copyProperties(association,associationVO);
        associationVO.setNewsInfos(newsInfos);
        associationVO.setActivities(activitiesDTO);
        return associationVO;
    }

    @Override
    public List<AssociationInfo> selectPop(Integer count) {
        return associationMapper.listPop(count);
    }

    @Override
    public List<AssociationInfo> selectByCategory(String category) {
        return associationMapper.listByCategory(category);
    }

    @Override
    public AssociationForm getAssociationForm(String associationId) {
        Association association = associationMapper.getById(associationId);
        AssociationForm associationForm = new AssociationForm();
        BeanUtils.copyProperties(association, associationForm);
        return associationForm;
    }

    @Override
    public List<AssociationInfo> getAssociationNames() {
        return associationMapper.getAssociationNames();
    }

    @Override
    public int updateApplyStatus(Integer status, String associationId) {
        return associationMapper.updateApplyStatus(status, associationId);
    }

    @Override
    @Transactional
    public int updateAssociationIcon(String associationId, Integer pictureId) {
        String picturePath = null;
        Picture picture;
        Association association = associationMapper.getById(associationId);
        if (association.getPictureId() != null){
            pictureMapper.updateIcon(association.getPictureId(), PictureIconEnum.NOT_ICON.getValue());
        }
        if (pictureId != null){
            picture = pictureMapper.getById(pictureId);
            if ( picture == null){
                throw new MyException(ResultEnum.PICTURE_NOT_EXIST);
            }
            if (picture.getIsIcon() != PictureIconEnum.NOT_ICON.getValue()){
                throw new MyException(ResultEnum.PICTURE_HAS_USED);
            }
            pictureMapper.updateIcon(pictureId, PictureIconEnum.ASSOCIATION_ICON.getValue());
            picturePath = picture.getPicturePath();
        }
        return associationMapper.updateAssociationIcon(pictureId, picturePath, associationId);
    }

    @Override
    @Transactional
    public int updateAssociationDesc(String associationId, String description) {
        Association association = associationMapper.getById(associationId);
        if (association == null) {
            throw new MyException(ResultEnum.ASSOCIATION_NOT_EXISTS);
        }
        return associationMapper.updateAssociationDesc(associationId, description);
    }

    @Override
    @Transactional
    public int updateAssociation(AssociationForm form) {
        Association association = associationMapper.getById(form.getAssociationId());
        if (association == null) {
            throw new MyException(ResultEnum.ASSOCIATION_NOT_EXISTS);
        }
        if (association.getPictureId() != null) {
            pictureMapper.updateIcon(association.getPictureId(), PictureIconEnum.NOT_ICON.getValue());
        }
        if (form.getPictureId() != null ){
            Picture newPicture = pictureMapper.getById(form.getPictureId());
            if (newPicture == null) {
                throw new MyException(ResultEnum.PICTURE_NOT_EXIST);
            }
            if (newPicture.getIsIcon() != 0){
                throw new MyException(ResultEnum.PICTURE_HAS_USED);
            }
            form.setPicturePath(newPicture.getPicturePath());
            pictureMapper.updateIcon(newPicture.getPictureId(), PictureIconEnum.ASSOCIATION_ICON.getValue());
        }
        return associationMapper.updateAssociation(form);
    }
}
