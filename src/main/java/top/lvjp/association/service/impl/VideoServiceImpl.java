package top.lvjp.association.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.VideoInfo;
import top.lvjp.association.entity.Association;
import top.lvjp.association.entity.User;
import top.lvjp.association.entity.Video;
import top.lvjp.association.mapper.AssociationMapper;
import top.lvjp.association.mapper.UserMapper;
import top.lvjp.association.mapper.VideoMapper;
import top.lvjp.association.service.VideoService;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private AssociationMapper associationMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<VideoInfo> listLatest(Integer count) {
        List<VideoInfo> videoInfos = videoMapper.listLatest(count);
        return videoInfos;
    }

    @Override
    public PageVO<VideoInfo> listAll(Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum, size);
        List<VideoInfo> videoInfos = videoMapper.listAll();
        PageInfo<VideoInfo> videoInfoPageInfo = new PageInfo<>(videoInfos);
        return new PageVO<>(videoInfoPageInfo);
    }

    @Override
    public VideoInfo getInfoById(Integer id) {
        Video video = videoMapper.getById(id);
        Association association = associationMapper.selectById(video.getAssociationId());
        String associationName = association.getAssociationName();
        User user = userMapper.selectById(video.getUserId());
        String userName = user.getUserName();
        VideoInfo videoInfo = new VideoInfo();
        BeanUtils.copyProperties(video,videoInfo);
        videoInfo.setAssociationName(associationName);
        videoInfo.setUserName(userName);
        return videoInfo;
    }

    @Override
    public PageVO<VideoInfo> listByAssociation(Integer associationId, Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum,size);
        List<VideoInfo> videoInfos = videoMapper.listByAssociation(associationId);
        PageInfo<VideoInfo> videoInfoPageInfo = new PageInfo<>(videoInfos);
        return new PageVO<>(videoInfoPageInfo);
    }

    @Override
    public int insert(Video video) {
        return videoMapper.insert(video);
    }

    @Override
    public int update(Video video) {
        return videoMapper.update(video);
    }

    @Override
    public int delete(Integer videoId, Integer associationId) {
        return videoMapper.delete(videoId,associationId);
    }
}
