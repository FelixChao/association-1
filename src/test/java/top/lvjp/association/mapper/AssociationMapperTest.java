package top.lvjp.association.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.lvjp.association.VO.AssociationInfo;
import top.lvjp.association.VO.AssociationVO;
import top.lvjp.association.VO.NewsInfo;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AssociationMapperTest {

    @Autowired
    private AssociationMapper associationMapper;

    @Autowired
    private NewsMapper newsMapper;

    @Test
    public void news(){
        List<NewsInfo> newsInfos = newsMapper.selectByAssociation(1);
        for (NewsInfo newsInfo: newsInfos) {
            System.out.println(newsInfo.getNewsTitle());
        }
        System.out.println();
    }

    @Test
    public void getAssociationById() {
        AssociationInfo association  = associationMapper.selectById(1);
        Assert.assertEquals(new Integer(1),association.getAssociationId());

    }

    @Test
    public void getPopAssociation() {
        List<AssociationInfo> associationInfos = associationMapper.selectPop(5);
        for (AssociationInfo associationInfo:associationInfos) {
            System.out.println(associationInfo.getAssociationName());
        }
        Assert.assertEquals(5,associationInfos.size());
    }

    @Test
    public void getAssociationByCategory() {
        List<AssociationInfo> associationInfos = associationMapper.selectByCategory("文艺类");
        Assert.assertEquals(2,associationInfos.size());
    }
}