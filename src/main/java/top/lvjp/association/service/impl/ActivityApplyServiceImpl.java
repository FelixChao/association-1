package top.lvjp.association.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lvjp.association.entity.Activity;
import top.lvjp.association.entity.ActivityApply;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.form.ActivityApplyForm;
import top.lvjp.association.mapper.ActivityApplyMapper;
import top.lvjp.association.mapper.ActivityMapper;
import top.lvjp.association.service.ActivityApplyService;

import java.util.Date;
import java.util.List;

@Service
public class ActivityApplyServiceImpl implements ActivityApplyService {

    @Autowired
    private ActivityApplyMapper activityApplyMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    @Transactional
    public void insertApply(ActivityApplyForm apply) {
        Integer id = apply.getActivityId();
        Activity activity = activityMapper.selectById(id);
        if (activity == null) {
            throw new MyException(ResultEnum.ACTIVITY_NOT_EXISTS);
        }
        else if(new Date().after(activity.getStartTime())){
            throw new MyException(ResultEnum.APPLY_IS_FINISHED);
        }
        else if(activity.getMaxApply() != null && activity.getMaxApply() <= activity.getApplyNumber()){
            throw new MyException(ResultEnum.APPLY_IS_FULL);
        }
        List<ActivityApply> activityApplies = activityApplyMapper.selectByActivityAndStudent(id,apply.getStudentNumber());
        if (!activityApplies.isEmpty()) {
            throw new MyException(ResultEnum.APPLY_ALREADY_EXISTS);
        }
        activityApplyMapper.insertApply(apply);
        activityMapper.addApply(id);
    }

    @Override
    public List<ActivityApply> selectByActivity(Integer id) {
        List<ActivityApply> activityApplies = activityApplyMapper.selectByActivity(id);
        return activityApplies;
    }
}
