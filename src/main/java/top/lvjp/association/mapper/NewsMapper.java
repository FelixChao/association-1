package top.lvjp.association.mapper;

import top.lvjp.association.VO.NewsInfo;

import java.util.List;

public interface NewsMapper {

    List<NewsInfo> selectLatest(Integer count);

    NewsInfo selectById(Integer id);

    List<NewsInfo> selectByAssociation(Integer id);

}
