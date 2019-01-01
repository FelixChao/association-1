package top.lvjp.association.mapper;

import org.apache.ibatis.annotations.Param;
import top.lvjp.association.VO.NewsInfo;
import top.lvjp.association.entity.News;
import top.lvjp.association.form.NewsForm;

import java.util.List;

public interface NewsMapper {

    List<NewsInfo> listLatest(Integer count);

    News getById(Integer id);

    List<NewsInfo> listByAssociation(String id);

    List<NewsInfo> listByStatus(@Param("status") Integer status, @Param("associationId") String associationId);

    List<NewsInfo> listByKey(@Param("key") String key, @Param("associationId") String associationId);

    int publish(@Param("id") Integer id);

    int update(NewsForm newsForm);

    int insert(NewsForm newsInfo);

    int delete(@Param("id") Integer id);

    List<NewsInfo> listAll();
}
