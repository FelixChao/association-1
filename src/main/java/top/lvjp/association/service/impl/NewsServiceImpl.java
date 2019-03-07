package top.lvjp.association.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lvjp.association.VO.NewsInfo;
import top.lvjp.association.VO.PageVO;
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
import top.lvjp.association.util.RightsUtil;

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
    public PageVO<NewsInfo> listByStatus(Integer status, String userAssociation, Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum,size);
        List<NewsInfo> newsInfos = newsMapper.listByStatus(status, userAssociation);
        PageInfo<NewsInfo> pageInfo = new PageInfo<>(newsInfos);
        return new PageVO<>(pageInfo);
    }

    @Override
    public PageVO<NewsInfo> queryByKey(String key, String userAssociation, Integer pageNum, Integer size) {
        key = "%" + key + "%";
        PageHelper.startPage(pageNum,size);
        List<NewsInfo> newsInfos = newsMapper.listByKey(key, userAssociation);
        PageInfo<NewsInfo> pageInfo = new PageInfo<>(newsInfos);
        return new PageVO<>(pageInfo);
    }

    @Override
    public int publish(Integer id, String userAssociation, Integer userType) {
        News news = newsMapper.getById(id);
        if(!RightsUtil.hasRights(userAssociation, news.getAssociationId(), userType)){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY);
        }
        return newsMapper.publish(id);
    }

    @Override
    @Transactional
    public int update(NewsForm newsForm, String userAssociation, Integer userType) {
        News news = newsMapper.getById(newsForm.getNewsId());
        if (RightsUtil.hasRights(userAssociation, news.getAssociationId(), userType)
                    || !news.getAssociationId().equals(newsForm.getAssociationId())){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY);
        }
        newsForm.setPicturePath(null);
        if (newsForm.getPictureId() != null){
            Picture picture = pictureMapper.getById(newsForm.getPictureId());
            if (picture != null){
                throw new MyException(ResultEnum.PICTURE_NOT_EXIST);
            }
            newsForm.setPicturePath(picture.getPicturePath());
        }
        return newsMapper.update(newsForm);
    }

    @Override
    @Transactional
    public int insert(NewsForm newsForm, String userAssociation, Integer userType) {
//        Association association = associationMapper.getById(newsForm.getAssociationId());
//        if (association == null) {
//            throw new MyException(ResultEnum.ASSOCIATION_NOT_EXISTS);
//        }
        if (!RightsUtil.hasRights(userAssociation, newsForm.getAssociationId(), userType)){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY.getCode(), "非最高管理员只能发布本社团新闻");
        }
        newsForm.setPicturePath(null);
        if (null != newsForm.getPictureId()){
            Picture picture = pictureMapper.getById(newsForm.getPictureId());
            if (picture != null){
                throw new MyException(ResultEnum.PICTURE_NOT_EXIST);
            }
            newsForm.setPicturePath(picture.getPicturePath());
        }
        return newsMapper.insert(newsForm);
    }

    @Override
    @Transactional
    public int delete(Integer id, String userAssociation, Integer userType) {
        News news = newsMapper.getById(id);
        if (RightsUtil.hasRights(userAssociation, news.getAssociationId(), userType)){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY);
        }
        return newsMapper.delete(id);
    }
}
