package top.lvjp.association.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.lvjp.association.entity.Picture;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PictureServiceTest {

    @Autowired
    private PictureService pictureService;

    @Test
    public void listAll() {
        int id = pictureService.listAll(1,10).getList().get(0).getPictureId();
        Assert.assertEquals(7,id);
    }

    @Test
    public void listByAssciation() {
        int id = pictureService.listByAssciation(1,1,10).getList().get(0).getPictureId();
        Assert.assertEquals(2,id);
    }

    @Test
    public void listAssociationIcon() {
        int id = pictureService.listAssociationIcon(1,10).getList().get(0).getId();
        Assert.assertEquals(1,id);
    }

    @Test
    public void listActivityIcon() {
        int id = pictureService.listActivityIcon(1,10).getList().get(0).getId();
        Assert.assertEquals(1,id);
    }

    @Test
    public void getById() {
        int id = pictureService.getById(1).getUserId();
        Assert.assertEquals(1, id);
    }

    @Ignore
    public void insert() {
        Picture p = new Picture();
        p.setAssociationId(2);
        p.setPictureCategory(1);
        p.setPicturePath("////");
        p.setUserId(1);
        p.setPictureTitle("null");
        int count = pictureService.insert(p);
        Assert.assertEquals(1,count);
    }

    @Test
    public void updateAssociationIcon() {
        int count = pictureService.updateAssociationIcon(null,1,1);
        Assert.assertEquals(1,count);
    }

    @Test
    public void updateActivityIcon() {
        int count = pictureService.updateActivityIcon(2,3,1,1);
        Assert.assertEquals(count,1);
    }

    @Test
    public void delete() {
        int count = pictureService.delete(new Integer[]{4,5},1);
        Assert.assertEquals(2,count);
    }
}