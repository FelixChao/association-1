package top.lvjp.association.service;

import top.lvjp.association.VO.VideoInfo;

import java.util.List;

public interface VideoService {

    /**
     * 查询最新视频
     * @return
     */
    List<VideoInfo> selectLatest();

    /**
     * 查询某一视频
     * @return
     */
    VideoInfo selectById(Integer id);
}
