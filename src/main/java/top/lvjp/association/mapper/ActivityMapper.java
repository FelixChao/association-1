package top.lvjp.association.mapper;

import org.apache.ibatis.annotations.Param;
import top.lvjp.association.VO.ActivityInfo;
import top.lvjp.association.entity.Activity;
import top.lvjp.association.form.ActivityForm;

import java.util.List;

public interface ActivityMapper {

    List<ActivityInfo> selectLatest(Integer count);

    //TODO 可合并为一个方法,使用choose
    List<ActivityInfo> selectCurrent();

    List<ActivityInfo> selectFuture();

    List<ActivityInfo> selectPast();

    List<ActivityInfo> selectCurrentByAssociation (Integer id);

    List<ActivityInfo> selectFutureByAssociation(Integer id);

    List<ActivityInfo> selectPastByAssociation(Integer id);

    List<ActivityInfo> selectCurrnetByStatus(@Param("status") Integer status,@Param("associationId") Integer associationId);

    List<ActivityInfo> selectFutureByStatus(@Param("status") Integer status,@Param("associationId") Integer associationId);

    List<ActivityInfo> selectPastByStatus(@Param("status") Integer status,@Param("associationId") Integer associationId);

    Activity selectById(Integer id);

    List<ActivityInfo> selectAll(@Param("associationId") Integer association,@Param("status") Integer status);

    List<ActivityInfo> queryByKey(@Param("key") String key,@Param("associationId") Integer associationId);

    int publish(@Param("id") Integer id,@Param("associationId") Integer associationId);

    int delete(@Param("id") Integer id,@Param("associationId") Integer associationId);

    int update(ActivityForm activityForm);

    int insert(ActivityForm activityForm);

    int addApply(Integer id);

    ActivityInfo selectByIdAndAssociation(@Param("activityId") Integer activityId, @Param("associationId") Integer associationId);
}
