package top.lvjp.association.mapper;

import top.lvjp.association.VO.NewsInfo;
import top.lvjp.association.entity.News;

import java.util.List;

public interface NewsMapper {

    List<NewsInfo> selectLatest(Integer count);

    News selectById(Integer id);

    List<NewsInfo> selectByAssociation(Integer id);

}
