package top.lvjp.association.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lvjp.association.VO.ActivityInfo;
import top.lvjp.association.mapper.ActivityMapper;
import top.lvjp.association.service.ActivityService;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

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
    public ActivityInfo selectById(Integer id) {
        return activityMapper.selectById(id);
    }

}
