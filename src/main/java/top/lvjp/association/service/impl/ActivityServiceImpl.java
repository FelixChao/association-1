package top.lvjp.association.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lvjp.association.VO.ActivityInfo;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.dto.ActivitiesDTO;
import top.lvjp.association.entity.Activity;
import top.lvjp.association.entity.Association;
import top.lvjp.association.entity.Picture;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.form.ActivityForm;
import top.lvjp.association.mapper.ActivityApplyMapper;
import top.lvjp.association.mapper.ActivityMapper;
import top.lvjp.association.mapper.AssociationMapper;
import top.lvjp.association.mapper.PictureMapper;
import top.lvjp.association.service.ActivityService;
import top.lvjp.association.util.RightsTestUtil;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private AssociationMapper associationMapper;

    @Autowired
    private ActivityApplyMapper activityApplyMapper;

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public List<ActivityInfo> selectLatest(Integer count) {
        return activityMapper.selectLatest(count);
    }

    @Override
    public PageVO<ActivityInfo> selectCurrent(Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum, size);
        List<ActivityInfo> activityInfos = activityMapper.selectCurrent();
        PageInfo<ActivityInfo> pageInfo = new PageInfo<>(activityInfos);
        return new PageVO<>(pageInfo);
    }

    @Override
    public PageVO<ActivityInfo> selectFuture(Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum, size);
        List<ActivityInfo> activityInfos = activityMapper.selectFuture();
        PageInfo<ActivityInfo> pageInfo = new PageInfo<>(activityInfos);
        return new PageVO<>(pageInfo);
    }

    @Override
    public PageVO<ActivityInfo> selectPast(Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum, size);
        List<ActivityInfo> activityInfos = activityMapper.selectPast();
        PageInfo<ActivityInfo> pageInfo = new PageInfo<>(activityInfos);
        return new PageVO<>(pageInfo);
    }

    @Override
    public ActivitiesDTO listAll() {
        List<ActivityInfo> current = activityMapper.selectCurrent();
        List<ActivityInfo> future = activityMapper.selectFuture();
        List<ActivityInfo> past = activityMapper.selectPast();
        return new ActivitiesDTO(current, future, past);
    }

    @Override
    public List<ActivityInfo> selectCurrentByAssociation(String id) {
        return activityMapper.selectCurrentByAssociation(id);
    }

    @Override
    public List<ActivityInfo> selectFutureByAssociation(String id) {
        return activityMapper.selectFutureByAssociation(id);
    }

    @Override
    public List<ActivityInfo> selectPastByAssociation(String id) {
        return activityMapper.selectPastByAssociation(id);
    }

    @Override
    public PageVO<ActivityInfo> selectCurrentByStatus(Integer status, String associationId,Integer pageNum,Integer size) {
        PageHelper.startPage(pageNum,size);
        List<ActivityInfo> activityInfo = activityMapper.selectCurrnetByStatus(status,associationId);
        PageInfo<ActivityInfo> pageInfo = new PageInfo<>(activityInfo);
        return new PageVO<>(pageInfo);
    }

    @Override
    public PageVO<ActivityInfo> selectFutureByStatus(Integer status, String associationId,Integer pageNum,Integer size) {
        PageHelper.startPage(pageNum,size);
        List<ActivityInfo> activityInfos = activityMapper.selectFutureByStatus(status,associationId);
        PageInfo<ActivityInfo> pageInfo = new PageInfo<>(activityInfos);
        return new PageVO<>(pageInfo);
    }

    @Override
    public PageVO<ActivityInfo> selectPastByStatus(Integer status, String associationId,Integer pageNum,Integer size) {
        PageHelper.startPage(pageNum,size);
        List<ActivityInfo> activityInfos = activityMapper.selectPastByStatus(status,associationId);
        PageInfo<ActivityInfo> pageInfo = new PageInfo<>(activityInfos);
        return new PageVO<>(pageInfo);
    }

    @Override
    @Transactional
    public ActivityInfo getInfoById(Integer id) {
        Activity activity = activityMapper.getById(id);
        if (activity == null) {
            throw new MyException(ResultEnum.ACTIVITY_NOT_EXISTS);
        }
        ActivityInfo activityInfo = new ActivityInfo();
        BeanUtils.copyProperties(activity,activityInfo);
        Association association = associationMapper.getById(activity.getAssociationId());
        activityInfo.setAssociationName(association.getAssociationName());
        return activityInfo;
    }

    @Override
    public List<ActivityInfo> selectAll(String associationId, Integer status) {
        return activityMapper.selectAll(associationId,status);
    }

    @Override
    public PageVO<ActivityInfo> queryByKey(String key,String associationId,Integer pageNum,Integer size) {
        key = "%"+key+"%";
        PageHelper.startPage(pageNum,size);
        List<ActivityInfo> activityInfos = activityMapper.queryByKey(key,associationId);
        PageInfo<ActivityInfo> pageInfo = new PageInfo<>(activityInfos);
        return new PageVO<>(pageInfo);
    }

    @Override
    @Transactional
    public int publish(Integer id,String associationId) {
        Activity activity = activityMapper.getById(id);
        if (!RightsTestUtil.hasRights(associationId, activity.getAssociationId())){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY);
        }
        return activityMapper.publish(id);
    }

    @Override
    @Transactional
    public int delete(Integer activityId,String associationId) {
        Activity activity = activityMapper.getById(activityId);
        if (activity == null) {
            throw new MyException(ResultEnum.ACTIVITY_NOT_EXISTS);
        }
        if (!RightsTestUtil.hasRights(associationId, activity.getAssociationId())){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY);
        }
        activityApplyMapper.deleteActivityApply(activityId);
        return activityMapper.delete(activityId);
    }

    @Override
    public ActivityForm getForm(Integer id) {
        Activity activity = activityMapper.getById(id);
        ActivityForm activityForm = new ActivityForm();
        BeanUtils.copyProperties(activity,activityForm);
        return activityForm;
    }

    @Override
    @Transactional
    public int update(ActivityForm activityForm, String associationId) {
//        Association associationName = associationMapper.getById(activityForm.getAssociationId());
//        if (associationName == null) {
//            throw new MyException(ResultEnum.ASSOCIATION_NOT_EXISTS);
//        }
        if (!RightsTestUtil.hasRights(associationId, activityForm.getAssociationId())){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY.getCode(), "非最高管理员只能将活动所属社团设为本社团!");
        }
        Activity activity = activityMapper.getById(activityForm.getActivityId());
        if (!RightsTestUtil.hasRights(associationId, activity.getAssociationId())){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY);
        }
        String picturePath = null;
        Picture picture;
        if (activityForm.getPictureId() != null){
            picture = pictureMapper.getById(activityForm.getPictureId());
            if (picture == null) {
                throw new MyException(ResultEnum.PICTURE_NOT_EXIST);
            }
            picturePath = picture.getPicturePath();
        }
        activityForm.setPicturePath(picturePath);
        return activityMapper.update(activityForm);
    }

    @Override
    @Transactional
    public int insert(ActivityForm activityForm, String userAssociation) {
//        Association associationName = associationMapper.getById(activityForm.getAssociationId());
//        if (associationName == null) {
//            throw new MyException(ResultEnum.ASSOCIATION_NOT_EXISTS);
//        }
        if (!RightsTestUtil.hasRights(userAssociation, activityForm.getAssociationId())){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY.getCode(), "非最高管理员只能将活动所属社团设为本社团!");
        }
        String picturePath = null;
        if (activityForm.getPictureId() != null){
            Picture picture = pictureMapper.getById(activityForm.getPictureId());
            if (picture == null) {
                throw new MyException(ResultEnum.PICTURE_NOT_EXIST);
            }
            picturePath = picture.getPicturePath();
        }
        activityForm.setPicturePath(picturePath);
        return activityMapper.insert(activityForm);
    }

    @Override
    @Transactional
    public int updateActivityIcon(Integer activityId, Integer pictureId, String userAssociation) {
        String picturePath = null;
        Activity activity = activityMapper.getById(activityId);
        if (!RightsTestUtil.hasRights(userAssociation, activity.getAssociationId())){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY);
        }
        if (pictureId != null){
            picturePath = pictureMapper.getById(pictureId).getPicturePath();
        }
        return activityMapper.updateActivityIcon(activityId, pictureId, picturePath);
    }
}
