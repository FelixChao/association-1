package top.lvjp.association.mapper;

import top.lvjp.association.VO.VideoInfo;

import java.util.List;

public interface VideoMapper {

    List<VideoInfo> selectLatest();

    VideoInfo selectById(Integer id);
}
