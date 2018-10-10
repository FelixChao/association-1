package top.lvjp.association.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lvjp.association.VO.VideoInfo;
import top.lvjp.association.mapper.VideoMapper;
import top.lvjp.association.service.VideoService;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<VideoInfo> selectLatest() {
        List<VideoInfo> videoInfos = videoMapper.selectLatest();
        return videoInfos;
    }

    @Override
    public VideoInfo selectById(Integer id) {
        VideoInfo videoInfo = videoMapper.selectById(id);
        return videoInfo;
    }
}
