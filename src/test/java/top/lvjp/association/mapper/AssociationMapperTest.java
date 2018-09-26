package top.lvjp.association.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.lvjp.association.dto.AssociationInfo;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AssociationMapperTest {

    @Autowired
    private AssociationMapper associationMapper;

    @Test
    public void getAssociationById() {
        AssociationInfo associationInfo  = associationMapper.selectAssociationById(1);
        Assert.assertEquals(new Integer(1),associationInfo.getAssociationId());

    }

    @Test
    public void getPopAssociation() {
        List<AssociationInfo> associationInfos = associationMapper.selectPopAssociation();
        for (AssociationInfo associationInfo:associationInfos) {
            System.out.println(associationInfo.getAssociationName());
        }
        Assert.assertEquals(2,associationInfos.size());
    }

    @Test
    public void getAssociationByCategory() {
        List<AssociationInfo> associationInfos = associationMapper.selectAssociationByCategory("a类");
        Assert.assertEquals(1,associationInfos.size());
    }
}