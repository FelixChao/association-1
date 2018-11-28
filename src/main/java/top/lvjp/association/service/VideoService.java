package top.lvjp.association.service;

import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.VideoInfo;
import top.lvjp.association.entity.Video;

import java.util.List;

public interface VideoService {

    /**
     * 查询最新视频
     * @return
     */
    List<VideoInfo> listLatest(Integer count);

    PageVO<VideoInfo> listAll(Integer pageNum, Integer size);

    /**
     * 查询某一视频
     * @return
     */
    VideoInfo getInfoById(Integer id);

    /**
     * 通过社团查询所有视频
     * @param associationId
     * @return
     */
    PageVO<VideoInfo> listByAssociation(Integer associationId, Integer pageNum, Integer size);

/// 相关信息已在前端展示
//    /**
//     * 获取活动的表单信息,
//     * @param videoId
//     * @param associationId
//     * @return
//     */
//    VideoForm getForm(Integer videoId, Integer associationId);

    /**
     * 上传一个视频
     * @param video
     * @return
     */
    int insert(Video video);


    /**
     * 修改视频信息
     * @param video
     * @return
     */
    int update(Video video);

    /**
     * 删除视频
     * 普通人只能删除本社团的视频
     * @param videoId
     * @param associationId
     * @return
     */
    int delete(Integer videoId, Integer associationId);
}
