package top.lvjp.association.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xerces.internal.dom.PSVIDOMImplementationImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.VideoInfo;
import top.lvjp.association.constant.SessionConstant;
import top.lvjp.association.entity.Association;
import top.lvjp.association.entity.User;
import top.lvjp.association.entity.Video;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.mapper.AssociationMapper;
import top.lvjp.association.mapper.UserMapper;
import top.lvjp.association.mapper.VideoMapper;
import top.lvjp.association.service.VideoService;
import top.lvjp.association.util.FileUtil;
import top.lvjp.association.util.RightsTestUtil;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private AssociationMapper associationMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FileUtil fileUtil;

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
        Association association = associationMapper.getById(video.getAssociationId());
        String associationName = association.getAssociationName();
        User user = userMapper.getById(video.getUserId());
        String userName = user.getUserName();
        VideoInfo videoInfo = new VideoInfo();
        BeanUtils.copyProperties(video,videoInfo);
        videoInfo.setAssociationName(associationName);
        videoInfo.setUserName(userName);
        return videoInfo;
    }

    @Override
    public PageVO<VideoInfo> listByAssociation(String associationId, Integer pageNum, Integer size) {
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
    @Transactional
    public int update(Video video, String associationId) {
        Video v = videoMapper.getById(video.getVideoId());
        if (!RightsTestUtil.hasRights(associationId, v.getAssociationId())){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY);
        }
        return videoMapper.update(video);
    }

    @Override
    @Transactional
    public int delete(Integer videoId, String associationId) {
        Video video = videoMapper.getById(videoId);
        if (!RightsTestUtil.hasRights(associationId, video.getAssociationId())){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY);
        }
        if (videoMapper.delete(videoId) == 1){
            fileUtil.deleteFile(video.getVideoPath(), FileUtil.VIDEO_FILE);
            return 1;
        }
        return 0;
    }
}
