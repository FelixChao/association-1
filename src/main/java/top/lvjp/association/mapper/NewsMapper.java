package top.lvjp.association.mapper;

import org.apache.ibatis.annotations.Param;
import top.lvjp.association.VO.NewsInfo;
import top.lvjp.association.entity.News;
import top.lvjp.association.form.NewsForm;

import java.util.List;

public interface NewsMapper {

    List<NewsInfo> selectLatest(Integer count);

    News selectById(Integer id);

    List<NewsInfo> selectByAssociation(Integer id);

    List<NewsInfo> selectByStatus(@Param("status") Integer status, @Param("associationId") Integer associationId);

    List<NewsInfo> selectByKey(@Param("key") String key, @Param("associationId") Integer associationId);

    int publish(@Param("id") Integer id,@Param("associationId") Integer associationId);

    int update(NewsForm newsForm);

    int insert(NewsForm newsInfo);

    int delete(@Param("id") Integer id,@Param("associationId") Integer associationId);
}
