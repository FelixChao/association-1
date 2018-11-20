package top.lvjp.association.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lvjp.association.VO.ActivityInfo;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.entity.Activity;
import top.lvjp.association.entity.Association;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.form.ActivityForm;
import top.lvjp.association.mapper.ActivityApplyMapper;
import top.lvjp.association.mapper.ActivityMapper;
import top.lvjp.association.mapper.AssociationMapper;
import top.lvjp.association.service.ActivityService;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private AssociationMapper associationMapper;

    @Autowired
    private ActivityApplyMapper activityApplyMapper;

    @Override
    public List<ActivityInfo> selectLatest(Integer count) {
        return activityMapper.selectLatest(count);
    }

    @Override
    public List<ActivityInfo> selectCurrent() {
        return activityMapper.selectCurrent();
    }

    @Override
    public List<ActivityInfo> selectFuture() {
        return activityMapper.selectFuture();
    }

    @Override
    public List<ActivityInfo> selectPast() {
        return activityMapper.selectPast();
    }

    @Override
    public List<ActivityInfo> selectCurrentByAssociation(Integer id) {
        return activityMapper.selectCurrentByAssociation(id);
    }

    @Override
    public List<ActivityInfo> selectFutureByAssociation(Integer id) {
        return activityMapper.selectFutureByAssociation(id);
    }

    @Override
    public List<ActivityInfo> selectPastByAssociation(Integer id) {
        return activityMapper.selectPastByAssociation(id);
    }

    @Override
    public PageVO<ActivityInfo> selectCurrentByStatus(Integer status, Integer associationId,Integer pageNum,Integer size) {
        PageHelper.startPage(pageNum,size);
        List<ActivityInfo> activityInfo = activityMapper.selectCurrnetByStatus(status,associationId);
        PageInfo<ActivityInfo> pageInfo = new PageInfo<>(activityInfo);
        return new PageVO<ActivityInfo>(pageInfo);
    }

    @Override
    public PageVO<ActivityInfo> selectFutureByStatus(Integer status, Integer associationId,Integer pageNum,Integer size) {
        PageHelper.startPage(pageNum,size);
        List<ActivityInfo> activityInfos = activityMapper.selectFutureByStatus(status,associationId);
        PageInfo<ActivityInfo> pageInfo = new PageInfo<ActivityInfo>(activityInfos);
        return new PageVO<ActivityInfo>(pageInfo);
    }

    @Override
    public PageVO<ActivityInfo> selectPastByStatus(Integer status, Integer associationId,Integer pageNum,Integer size) {
        PageHelper.startPage(pageNum,size);
        List<ActivityInfo> activityInfos = activityMapper.selectPastByStatus(status,associationId);
        PageInfo<ActivityInfo> pageInfo = new PageInfo<>(activityInfos);
        return new PageVO<ActivityInfo>(pageInfo);
    }

    @Override
    public ActivityInfo getInfoById(Integer id) {
        Activity activity = activityMapper.selectById(id);
        if (activity == null) {
            throw new MyException(ResultEnum.ACTIVITY_NOT_EXISTS);
        }
        ActivityInfo activityInfo = new ActivityInfo();
        BeanUtils.copyProperties(activity,activityInfo);
        return activityInfo;
    }

    @Override
    public List<ActivityInfo> selectAll(Integer associationId, Integer status) {
        return activityMapper.selectAll(associationId,status);
    }

    @Override
    public PageVO<ActivityInfo> queryByKey(String key,Integer associationId,Integer pageNum,Integer size) {
        key = "%"+key+"%";
        PageHelper.startPage(pageNum,size);
        List<ActivityInfo> activityInfos = activityMapper.queryByKey(key,associationId);
        PageInfo<ActivityInfo> pageInfo = new PageInfo<>(activityInfos);
        return new PageVO<ActivityInfo>(pageInfo);
    }

    @Override
    public int publish(Integer id,Integer associationId) {
        return activityMapper.publish(id,associationId);
    }

    @Override
    @Transactional
    public boolean delete(Integer activityId,Integer associationId) {
        if (activityMapper.selectByIdAndAssociation(activityId,associationId) != null){
            activityApplyMapper.deleteActivityApply(activityId);
            activityMapper.delete(activityId,associationId);
            return true;
        }
        return false;
    }

    @Override
    public ActivityForm getForm(Integer id) {
        Activity activity = activityMapper.selectById(id);
        if (activity == null) {
            throw new MyException(ResultEnum.ACTIVITY_NOT_EXISTS);
        }
        ActivityForm activityForm = new ActivityForm();
        BeanUtils.copyProperties(activity,activityForm);
        return activityForm;
    }

    @Override
    public int update(ActivityForm activityForm) {
        Association association = associationMapper.selectById(activityForm.getAssociationId());
        if (association == null) {
            throw new MyException(ResultEnum.ASSOCIATION_NOT_EXISTS);
        }
        return activityMapper.update(activityForm);
    }

    @Override
    public int insert(ActivityForm activityForm) {
        Association association = associationMapper.selectById(activityForm.getAssociationId());
        if (association == null) {
            throw new MyException(ResultEnum.ASSOCIATION_NOT_EXISTS);
        }
        return activityMapper.insert(activityForm);
    }
}
