package top.lvjp.association.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.constant.SessionConstant;
import top.lvjp.association.entity.Activity;
import top.lvjp.association.entity.ActivityApply;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.enums.UserTypeEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.form.ActivityApplyForm;
import top.lvjp.association.form.QueryForm;
import top.lvjp.association.mapper.ActivityApplyMapper;
import top.lvjp.association.mapper.ActivityMapper;
import top.lvjp.association.service.ActivityApplyService;
import top.lvjp.association.util.RightsUtil;

import java.util.List;

@Service
public class ActivityApplyServiceImpl implements ActivityApplyService {

    @Autowired
    private ActivityApplyMapper activityApplyMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    @Transactional
    public int insertApply(ActivityApplyForm apply) {
        Integer id = apply.getActivityId();
        Activity activity = activityMapper.getById(id);
        if (activity == null) {
            throw new MyException(ResultEnum.ACTIVITY_NOT_EXISTS);
        }
        if(activity.getMaxApply() != null && activity.getMaxApply() <= activity.getApplyNumber()){
            throw new MyException(ResultEnum.APPLY_IS_FULL);
        }
        List<ActivityApply> activityApplies = activityApplyMapper.selectByActivityAndStudent(id,apply.getStudentNumber());
        if (!activityApplies.isEmpty()) {
            throw new MyException(ResultEnum.APPLY_ALREADY_EXISTS);
        }
        activityMapper.addApply(id);
        return activityApplyMapper.insertApply(apply);
    }

    @Override
    @Transactional
    public PageVO<ActivityApply> getByActivity(String userAssociation, Integer userType, Integer activityId, Integer pageNum, Integer size) {
        Activity activity = activityMapper.getById(activityId);
        if (activity == null) {
            throw new MyException(ResultEnum.ACTIVITY_NOT_EXISTS);
        }
        if (!RightsUtil.hasRights(userAssociation, activity.getAssociationId(), userType)){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY);
        }
        PageHelper.startPage(pageNum,size);
        List<ActivityApply> activityApplies = activityApplyMapper.selectByActivity(activityId);
        PageInfo<ActivityApply> activityApplyPageInfo = new PageInfo<>(activityApplies);
        return new PageVO<>(activityApplyPageInfo);
    }

    @Override
    @Transactional
    public PageVO<ActivityApply> query(QueryForm queryForm, String associationId, Integer userType, Integer pageNum, Integer size) {
        if (queryForm.getActivityId() != null){
            Activity activity = activityMapper.getById(queryForm.getActivityId());
            if (!RightsUtil.hasRights(associationId, activity.getAssociationId(), userType)){
                throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY);
            }
        } else if (userType.equals(UserTypeEnum.ROOT.getValue())){
                associationId = null;
        }
        queryForm.setStudentName(queryForm.getStudentName() == null ? null : "%" + queryForm.getStudentName() + "%");
        queryForm.setStudentNumber(queryForm.getStudentNumber() == null ? null : "%"
                + queryForm.getStudentNumber() + "%");
        queryForm.setAcademy(queryForm.getAcademy() == null ? null : "%" + queryForm.getAcademy() + "%");
        queryForm.setQq(queryForm.getQq() == null ? null : "%" + queryForm.getQq() + "%");
        queryForm.setPhone(queryForm.getPhone() == null ? null : "%" + queryForm.getPhone() + "%");
        PageHelper.startPage(pageNum,size);
        List<ActivityApply> activityApplies = activityApplyMapper.query(queryForm, associationId);
        PageInfo<ActivityApply> activityApplyPageInfo = new PageInfo<>(activityApplies);
        return new PageVO<>(activityApplyPageInfo);
    }

    @Override
    @Transactional
    public int deleteByActivityId(Integer activityId, String userAssociation, Integer userType) {
        Activity activity = activityMapper.getById(activityId);
        if (!RightsUtil.hasRights(userAssociation, activity.getAssociationId(), userType)){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY);
        }
        int count = activityApplyMapper.deleteActivityApply(activityId);
        activityMapper.reduceApply(activityId, count);
        return count;
    }

    @Override
    @Transactional
    public int deleteByIds(Integer[] ids, Integer activityId, String userAssociation, Integer userType) {
        Activity activity = activityMapper.getById(activityId);
        if (!RightsUtil.hasRights(userAssociation, activity.getAssociationId(), userType)){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY);
        }
        return activityApplyMapper.deleteByIds(ids, activityId);
    }
}