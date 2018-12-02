package top.lvjp.association.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lvjp.association.VO.NewsInfo;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.entity.Association;
import top.lvjp.association.entity.News;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.form.NewsForm;
import top.lvjp.association.mapper.AssociationMapper;
import top.lvjp.association.mapper.NewsMapper;
import top.lvjp.association.service.NewsService;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private AssociationMapper associationMapper;

    @Override
    public List<NewsInfo> selectLatest(Integer count) {
        return newsMapper.selectLatest(count);
    }

    @Override
    public PageVO<NewsInfo> listAll(Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum, size);
        List<NewsInfo> newsInfos = newsMapper.listAll();
        PageInfo<NewsInfo> pageInfo = new PageInfo<>(newsInfos);
        return new PageVO<>(pageInfo);
    }

    @Override
    public NewsInfo getInfoById(Integer id) {
        News news = newsMapper.selectById(id);
        NewsInfo newsInfo = new NewsInfo();
        BeanUtils.copyProperties(news,newsInfo);
        return newsInfo;
    }

    @Override
    public NewsForm getFormById(Integer id) {
        News news = newsMapper.selectById(id);
        if (news == null) {
            throw new MyException(ResultEnum.NEWS_NOT_EXISTS);
        }
        NewsForm newsForm = new NewsForm();
        BeanUtils.copyProperties(news,newsForm);
        return newsForm;
    }

    @Override
    public PageVO<NewsInfo> selectByStatus(Integer status, Integer associationId,Integer pageNum,Integer size) {
        PageHelper.startPage(pageNum,size);
        List<NewsInfo> newsInfos = newsMapper.selectByStatus(status,associationId);
        PageInfo<NewsInfo> pageInfo = new PageInfo<>(newsInfos);
        return new PageVO<NewsInfo>(pageInfo);
    }

    @Override
    public PageVO<NewsInfo> queryByKey(String key, Integer associationId, Integer pageNum, Integer size) {
        key = "%" + key + "%";
        PageHelper.startPage(pageNum,size);
        List<NewsInfo> newsInfos = newsMapper.selectByKey(key,associationId);
        PageInfo<NewsInfo> pageInfo = new PageInfo<>(newsInfos);
        return new PageVO<NewsInfo>(pageInfo);
    }

    @Override
    public int publish(Integer id, Integer associationId) {
        return newsMapper.publish(id,associationId);
    }

    @Override
    public int update(NewsForm newsForm) {
        Association association = associationMapper.selectById(newsForm.getAssociationId());
        if (association == null) {
            throw new MyException(ResultEnum.ASSOCIATION_NOT_EXISTS);
        }
        return newsMapper.update(newsForm);
    }

    @Override
    public int insert(NewsForm newsForm) {
        Association association = associationMapper.selectById(newsForm.getAssociationId());
        if (association == null) {
            throw new MyException(ResultEnum.ASSOCIATION_NOT_EXISTS);
        }
        return newsMapper.insert(newsForm);
    }

    @Override
    public int delete(Integer id, Integer associationId) {
        return newsMapper.delete(id,associationId);
    }
}
