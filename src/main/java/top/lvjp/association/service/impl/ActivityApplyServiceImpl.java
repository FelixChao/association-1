package top.lvjp.association.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lvjp.association.VO.ActivityInfo;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.entity.Activity;
import top.lvjp.association.entity.ActivityApply;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.form.ActivityApplyForm;
import top.lvjp.association.form.QueryForm;
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
//        else if(new Date().after(activity.getStartTime())){
//            throw new MyException(ResultEnum.APPLY_IS_FINISHED);
//        }
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
    public PageVO<ActivityApply> selectByActivity(Integer associationId, Integer activityId,Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum,size);
        List<ActivityApply> activityApplies = activityApplyMapper.selectByActivity(activityId,associationId);
        PageInfo<ActivityApply> activityApplyPageInfo = new PageInfo<>(activityApplies);
        return new PageVO<ActivityApply>(activityApplyPageInfo);
    }

    @Override
    public PageVO<ActivityApply> query(QueryForm queryForm, Integer associationId, Integer pageNum, Integer size) {
        queryForm.setStudentName(queryForm.getStudentName() == null ? null : "%" + queryForm.getStudentName() + "%");
        queryForm.setStudentNumber(queryForm.getStudentNumber() == null ? null : "%"
                + queryForm.getStudentNumber() + "%");
        queryForm.setAcademy(queryForm.getAcademy() == null ? null : "%" + queryForm.getAcademy() + "%");
        PageHelper.startPage(pageNum,size);
        List<ActivityApply> activityApplies = activityApplyMapper.query(queryForm,associationId);
        PageInfo<ActivityApply> activityApplyPageInfo = new PageInfo<>(activityApplies);
        return new PageVO<ActivityApply>(activityApplyPageInfo);
    }
}
