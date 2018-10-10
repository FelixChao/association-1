package top.lvjp.association.service;

import top.lvjp.association.VO.NewsInfo;

import java.util.List;

public interface NewsService {

    /**
     * 查询最近的新闻
     * @param count 查询数量
     * @return List &lt;NewsInfo&gt;
     */
    List<NewsInfo> selectLatest(Integer count);

    /**
     * 通过id查询某一新闻
     * @param id
     * @return NewsInfo
     */
    NewsInfo selectById(Integer id);

    /**
     * 查询某一社团最近的新闻
     * @param id 社团编号
     * @param count 查询数量
     * @return List &lt;NewsInfo&gt;
     */
    List<NewsInfo> selectByAssociation(Integer id,Integer count);
}
