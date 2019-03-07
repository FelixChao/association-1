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
    List<NewsInfo> listLatest(Integer count);

    PageVO<NewsInfo> listAll(Integer pageNum, Integer size);

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
     * @param userAssociation 用户社团编号
     * @return PageVO&lt;NewsInof&gt;
     */
    PageVO<NewsInfo> listByStatus(Integer status, String userAssociation, Integer pageNum, Integer size);

    /**
     * 通过关键字 key 查询社团的新闻
     * @param key 查询关键字
     * @param userAssociation 用户社团编号
     * @return PageVO&lt;NewsInfo&gt;
     */
    PageVO<NewsInfo> queryByKey(String key, String userAssociation, Integer pageNum, Integer size);

    /**
     * 发布新闻
     * @param id 新闻编号
     * @param userAssociation 用户社团编号
     * @param userType 用户类型
     * @return
     */
    int publish(Integer id, String userAssociation, Integer userType);

    /**
     * 更新新闻
     * 非最高管理员只能修改本社团新闻, 且不能修改所属社团
     * @param newsForm 新闻表单
     * @param userAssociation 用户所属社团
     * @param userType
     * @return 成功返回1,失败0
     */
    int update(NewsForm newsForm, String userAssociation, Integer userType);

    /**
     * 填写新闻
     * 非最高管理员只能填写本社团新闻
     * @param newsForm 新闻表单
     * @param userAssociation
     * @param userType
     * @return
     */
    int insert(NewsForm newsForm, String userAssociation, Integer userType);

    /**
     * 删除新闻
     * @param id 新闻编号
     * @param userAssociation 用户社团编号
     * @param userType
     * @return
     */
    int delete(Integer id,String userAssociation, Integer userType);
}
