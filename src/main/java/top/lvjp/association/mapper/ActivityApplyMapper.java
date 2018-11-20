package top.lvjp.association.mapper;

import org.apache.ibatis.annotations.Param;
import top.lvjp.association.entity.ActivityApply;
import top.lvjp.association.form.ActivityApplyForm;
import top.lvjp.association.form.QueryForm;

import java.util.List;

public interface ActivityApplyMapper {

    int insertApply(ActivityApplyForm apply);

    List<ActivityApply> selectByActivityAndStudent(@Param("activityId") Integer id, @Param("studentNumber") String studentNumber);

    List<ActivityApply> selectByActivity(@Param("activityId") Integer activityId, @Param("associationId") Integer associationId);

    List<ActivityApply> query(@Param("query") QueryForm queryForm, @Param("associationId") Integer associationId);

    int deleteActivityApply(Integer activityId);
}
