package top.lvjp.association.mapper;

import top.lvjp.association.VO.ActivityInfo;
import top.lvjp.association.entity.Activity;

import java.util.List;

public interface ActivityMapper {

    List<ActivityInfo> selectLatest(Integer count);

    List<ActivityInfo> selectCurrent();

    List<ActivityInfo> selectFuture();

    List<ActivityInfo> selectPast();

    List<ActivityInfo> selectCurrentByAssociation (Integer id);

    List<ActivityInfo> selectFutureByAssociation(Integer id);

    List<ActivityInfo> selectPastByAssociation(Integer id);

    ActivityInfo selectById(Integer id);

    int update(Activity activity);
}
