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

    List<ActivityInfo> selectCurrentByAssociation (String id);

    List<ActivityInfo> selectFutureByAssociation(String id);

    List<ActivityInfo> selectPastByAssociation(String id);

    List<ActivityInfo> selectCurrnetByStatus(@Param("status") Integer status,@Param("associationId") String associationId);

    List<ActivityInfo> selectFutureByStatus(@Param("status") Integer status,@Param("associationId") String associationId);

    List<ActivityInfo> selectPastByStatus(@Param("status") Integer status,@Param("associationId") String associationId);

    Activity getById(Integer id);

    List<ActivityInfo> selectAll(@Param("associationId") String association,@Param("status") Integer status);

    List<ActivityInfo> queryByKey(@Param("key") String key,@Param("associationId") String associationId);

    int publish(@Param("id") Integer id);

    int delete(@Param("id") Integer id);

    int update(ActivityForm activityForm);

    int insert(ActivityForm activityForm);

    int addApply(Integer id);

//    ActivityInfo selectByIdAndAssociation(@Param("activityId") Integer activityId, @Param("associationId") Integer associationId);

//    List<PictureInfo> listActivityIcon();

    int updateActivityIcon(@Param("activityId") Integer activityId, @Param("pictureId") Integer pictureId, @Param("picturePath") String picturePath);

    int reduceApply(@Param("activityId") Integer activityId, @Param("count") int count);
}
