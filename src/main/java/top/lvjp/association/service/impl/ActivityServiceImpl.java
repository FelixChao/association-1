package top.lvjp.association.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lvjp.association.VO.ActivityInfo;
import top.lvjp.association.entity.Activity;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.enums.TextStatusEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.form.ActivityForm;
import top.lvjp.association.mapper.ActivityMapper;
import top.lvjp.association.service.ActivityService;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public List<ActivityInfo> selectLatest(Integer count) {
        if (count == null || count <0) {
            count = 10;
        }
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
    public List<ActivityInfo> selectCurrnetByStatus(Integer status, Integer associationId) {
        return activityMapper.selectCurrnetByStatus(status,associationId);
    }

    @Override
    public List<ActivityInfo> selectFutureByStatus(Integer status, Integer associationId) {
        return activityMapper.selectFutureByStatus(status,associationId);
    }

    @Override
    public List<ActivityInfo> selectPastByStatus(Integer status, Integer associationId) {
        return activityMapper.selectPastByStatus(status,associationId);
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
    public int publish(Integer id, TextStatusEnum status) {
        return activityMapper.publish(id,status.getStatus());
    }

    @Override
    public int delete(Integer id) {
        return activityMapper.delete(id);
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
        return activityMapper.update(activityForm);
    }

    @Override
    public int insert(ActivityForm activityForm) {
        return activityMapper.insert(activityForm);
    }
}
