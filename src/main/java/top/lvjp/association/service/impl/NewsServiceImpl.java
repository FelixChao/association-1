package top.lvjp.association.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import top.lvjp.association.VO.NewsInfo;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.constant.SessionConstant;
import top.lvjp.association.entity.Association;
import top.lvjp.association.entity.News;
import top.lvjp.association.entity.Picture;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.form.NewsForm;
import top.lvjp.association.mapper.AssociationMapper;
import top.lvjp.association.mapper.NewsMapper;
import top.lvjp.association.mapper.PictureMapper;
import top.lvjp.association.service.NewsService;
import top.lvjp.association.util.ResultUtil;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private AssociationMapper associationMapper;

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public List<NewsInfo> listLatest(Integer count) {
        return newsMapper.listLatest(count);
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
        News news = newsMapper.getById(id);
        if (!news.getNewsStatus().equals(1)){
            return null;
        }
        NewsInfo newsInfo = new NewsInfo();
        BeanUtils.copyProperties(news,newsInfo);
        Association association = associationMapper.getById(news.getAssociationId());
        newsInfo.setAssociationName(association.getAssociationName());
        return newsInfo;
    }

    @Override
    public NewsForm getFormById(Integer id) {
        News news = newsMapper.getById(id);
        NewsForm newsForm = new NewsForm();
        BeanUtils.copyProperties(news,newsForm);
        return newsForm;
    }

    @Override
    public PageVO<NewsInfo> listByStatus(Integer status, String associationId, Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum,size);
        List<NewsInfo> newsInfos = newsMapper.listByStatus(status,associationId);
        PageInfo<NewsInfo> pageInfo = new PageInfo<>(newsInfos);
        return new PageVO<>(pageInfo);
    }

    @Override
    public PageVO<NewsInfo> queryByKey(String key, String associationId, Integer pageNum, Integer size) {
        key = "%" + key + "%";
        PageHelper.startPage(pageNum,size);
        List<NewsInfo> newsInfos = newsMapper.listByKey(key,associationId);
        PageInfo<NewsInfo> pageInfo = new PageInfo<>(newsInfos);
        return new PageVO<>(pageInfo);
    }

    @Override
    public int publish(Integer id, String associationId) {
        News news = newsMapper.getById(id);
        if (!associationId.equals(SessionConstant.ROOT_ASSOCIATION_VALUE)
                && !associationId.equals(news.getAssociationId())){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY);
        }
        return newsMapper.publish(id);
    }

    @Override
    @Transactional
    public int update(NewsForm newsForm, String associationId) {
        News news = newsMapper.getById(newsForm.getNewsId());
        if (!associationId.equals(SessionConstant.ROOT_ASSOCIATION_VALUE)
                && (!associationId.equals(news.getAssociationId())
                    || !associationId.equals(newsForm.getAssociationId()))){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY);
        }
        if (news.getPictureId() != null){
            pictureMapper.reduceIconCount(news.getPictureId());
        }
        newsForm.setPicturePath(null);
        if (newsForm.getPictureId() != null){
            Picture picture = pictureMapper.getById(newsForm.getPictureId());
            if (picture != null){
                throw new MyException(ResultEnum.PICTURE_NOT_EXSIST);
            }
            pictureMapper.addIconCount(picture.getPictureId());
            newsForm.setPicturePath(picture.getPicturePath());
        }
        return newsMapper.update(newsForm);
    }

    @Override
    @Transactional
    public int insert(NewsForm newsForm, String userAssociation) {
        Association association = associationMapper.getById(newsForm.getAssociationId());
        if (association == null) {
            throw new MyException(ResultEnum.ASSOCIATION_NOT_EXISTS);
        }
        if (!userAssociation.equals(newsForm.getAssociationId())
                && !userAssociation.equals(SessionConstant.ROOT_ASSOCIATION_VALUE)){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY.getCode(), "非最高管理员只能发布本社团新闻");
        }
        newsForm.setPicturePath(null);
        if (null != newsForm.getPictureId()){
            Picture picture = pictureMapper.getById(newsForm.getPictureId());
            if (picture != null){
                throw new MyException(ResultEnum.PICTURE_NOT_EXSIST);
            }
            pictureMapper.addIconCount(picture.getPictureId());
            newsForm.setPicturePath(picture.getPicturePath());
        }
        return newsMapper.insert(newsForm);
    }

    @Override
    @Transactional
    public int delete(Integer id, String associationId) {
        News news = newsMapper.getById(id);
        if (!associationId.equals(SessionConstant.ROOT_ASSOCIATION_VALUE)
                && !associationId.equals(news.getAssociationId())){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY);
        }
        if (news.getPictureId() != null) {
            pictureMapper.reduceIconCount(news.getPictureId());
        }
        return newsMapper.delete(id);
    }
}
