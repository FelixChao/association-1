package top.lvjp.association.service;

import top.lvjp.association.entity.ActivityApply;
import top.lvjp.association.form.ActivityApplyForm;

import java.util.List;

public interface ActivityApplyService {

    void insertApply(ActivityApplyForm apply);

    List<ActivityApply> selectByActivity(Integer id);
}
