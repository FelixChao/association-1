package top.lvjp.association.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public List<VideoInfo> selectLatest(Integer count) {
        List<VideoInfo> videoInfos = videoMapper.selectLatest(count);
        return videoInfos;
    }

    @Override
    public VideoInfo getInfoById(Integer id) {
        Video video = videoMapper.selectById(id);
        Association association = associationMapper.selectById(video.getAssociationId());
        String associationName = association.getAssociationName();
        User user = userMapper.selectById(video.getUserId());
        String userName = user.getUserName();
        VideoInfo videoInfo = new VideoInfo();
        BeanUtils.copyProperties(video,videoInfo);
        videoInfo.setAssociation(associationName);
        videoInfo.setAuthor(userName);
        return videoInfo;
    }
}
