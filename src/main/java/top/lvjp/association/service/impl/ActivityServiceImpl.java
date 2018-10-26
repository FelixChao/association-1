package top.lvjp.association.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lvjp.association.VO.ActivityInfo;
import top.lvjp.association.entity.Activity;
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
    public ActivityInfo getInfoById(Integer id) {
        Activity activity = activityMapper.selectById(id);
        ActivityInfo activityInfo = new ActivityInfo();
        BeanUtils.copyProperties(activity,activityInfo);
        return activityInfo;
    }

}
