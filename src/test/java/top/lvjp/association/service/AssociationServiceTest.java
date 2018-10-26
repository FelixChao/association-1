package top.lvjp.association.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.lvjp.association.VO.AssociationInfo;
import top.lvjp.association.VO.AssociationVO;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@MapperScan("top.lvjp.association.mapper")
public class AssociationServiceTest {

    @Autowired
    private AssociationService associationService;

    @Test
    public void selectAssociationById() {
        AssociationVO association = associationService.getVOById(1);
        Assert.assertEquals("羽毛球协会",association.getAssociationName());
    }

    @Test
    public void selectPopAssociation() {
        List<AssociationInfo> associations = associationService.selectPop(5);
        Assert.assertEquals("工大音乐社",associations.get(4).getAssociationName());
    }

    @Test
    public void selectAssociationByCategory() {
        List<AssociationInfo> associations = associationService.selectByCategory("文艺类");
        Assert.assertEquals("工大音乐社",associations.get(1).getAssociationName());
    }
}