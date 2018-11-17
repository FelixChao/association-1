package top.lvjp.association.service;

import top.lvjp.association.VO.NewsInfo;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.form.NewsForm;

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
     * @param id 新闻编号
     * @return NewsInfo
     */
    NewsInfo getInfoById(Integer id);

    /**
     * 获取新闻的表单信息
     * @param id 新闻的编号
     * @return newsform
     */
    NewsForm getFormById(Integer id);

    /**
     * 查询社团文章状态为 status 的新闻
     * @param status 文章发布状态
     * @param associationId 社团编号
     * @return PageVO&lt;NewsInof&gt;
     */
    PageVO<NewsInfo> selectByStatus(Integer status,Integer associationId,Integer pageNum,Integer size);

    /**
     * 通过关键字 key 查询社团的新闻
     * @param key 查询关键字
     * @param associationId 社团编号
     * @return PageVO&lt;NewsInfo&gt;
     */
    PageVO<NewsInfo> queryByKey(String key, Integer associationId, Integer pageNum, Integer size);

    /**
     * 发布新闻
     * @param id 新闻编号
     * @param associationId 社团编号
     * @return 成功返回1,失败0
     */
    int publish(Integer id, Integer associationId);

    /**
     * 发布新闻
     * @param newsForm 新闻表单
     * @return 成功返回1,失败0
     */
    int update(NewsForm newsForm);

    /**
     * 发布新闻
     * @param newsForm 新闻表单
     * @return 成功返回1,失败0
     */
    int insert(NewsForm newsForm);

    /**
     * 删除新闻
     * @param id 新闻编号
     * @param associationId 社团编号
     * @return 成功返回1,失败0
     */
    int delete(Integer id,Integer associationId);
}
