package top.lvjp.association.mapper;

import org.apache.ibatis.annotations.Param;
import top.lvjp.association.entity.ActivityApply;
import top.lvjp.association.form.ActivityApplyForm;

import java.util.List;

public interface ActivityApplyMapper {

    int insertApply(ActivityApplyForm apply);

    List<ActivityApply> selectByActivity(Integer activityId);

    List<ActivityApply> selectByActivityAndStudent(@Param("activityId") Integer id, @Param("studentNumber") String studentNumber);
}
