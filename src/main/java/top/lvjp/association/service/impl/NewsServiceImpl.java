package top.lvjp.association.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lvjp.association.VO.NewsInfo;
import top.lvjp.association.mapper.NewsMapper;
import top.lvjp.association.service.NewsService;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<NewsInfo> selectLatest(Integer count) {
        return newsMapper.selectLatest(count);
    }

    @Override
    public NewsInfo selectById(Integer id) {
        return newsMapper.selectById(id);
    }

    @Override
    public List<NewsInfo> selectByAssociation(Integer id,Integer count) {
        return newsMapper.selectByAssociation(id);
    }
}
