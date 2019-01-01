package top.lvjp.association.mapper;

import org.apache.ibatis.annotations.Param;
import top.lvjp.association.VO.VideoInfo;
import top.lvjp.association.entity.Video;

import java.util.List;

public interface VideoMapper {

    List<VideoInfo> listLatest(Integer count);

    List<VideoInfo> listAll();

    Video getById(Integer id);

    List<VideoInfo> listByAssociation(@Param("associationId") String associationId);

    int insert(Video video);

    int update(Video video);

    int delete(@Param("videoId") Integer videoId);
}
