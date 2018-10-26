package top.lvjp.association.mapper;

import top.lvjp.association.VO.VideoInfo;
import top.lvjp.association.entity.Video;

import java.util.List;

public interface VideoMapper {

    List<VideoInfo> selectLatest(Integer count);

    Video selectById(Integer id);
}
