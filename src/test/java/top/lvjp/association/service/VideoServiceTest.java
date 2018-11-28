package top.lvjp.association.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.VideoInfo;
import top.lvjp.association.entity.Video;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VideoServiceTest {

    @Autowired
    private VideoService videoService;

    @Test
    public void listLatest() {
        List<VideoInfo> infos = videoService.listLatest(10);
        Assert.assertEquals(new Integer(1),infos.get(1).getVideoId());
    }

    @Test
    public void listAll() {
        PageVO<VideoInfo> infos = videoService.listAll(1,10);
        Assert.assertEquals("羽毛球协会",infos.getList().get(1).getAssociationName());
    }

    @Test
    public void listByAssociation() {
        PageVO<VideoInfo> infos = videoService.listByAssociation(1,1,10);
        Assert.assertEquals("admin",infos.getList().get(0).getUserName());
    }

    @Ignore
    public void insert() {
        Video video = new Video();
        video.setAssociationId(2);
        video.setVideoTitle("moshu");
        video.setVideoPath("////");
        video.setUserId(2);
        int count = videoService.insert(video);
        Assert.assertEquals(1,count);
    }

    @Ignore
    public void update() {
        Video video = new Video();
        video.setVideoId(3);
        video.setAssociationId(2);
        video.setVideoTitle("moshu");
        video.setVideoDescription("haokan");
        int count = videoService.update(video);
        Assert.assertEquals(1,count);
    }

    @Ignore
    public void delete() {
        int count = videoService.delete(2,2);
        Assert.assertEquals(1,count);
    }
}