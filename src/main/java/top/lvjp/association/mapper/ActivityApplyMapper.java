package top.lvjp.association.mapper;

import top.lvjp.association.entity.ActivityApply;
import top.lvjp.association.form.ActivityApplyForm;

import java.util.List;

public interface ActivityApplyMapper {

    int insertApply(ActivityApplyForm apply);

    List<ActivityApply> selectByActivity(Integer id);
}
