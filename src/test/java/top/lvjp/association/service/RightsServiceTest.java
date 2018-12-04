package top.lvjp.association.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.lvjp.association.form.RightsForm;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RightsServiceTest {

    @Autowired
    private RightsService rightsService;

    @Test
    public void insertRights() {
        RightsForm form = new RightsForm();
        form.setAssociationId(1);
        form.setEmail("124@23.com");
        form.setPhone("15225252525");
        form.setRightsContent("null");
        form.setRightsTitle("title");
        int count = rightsService.insertRights(form);
        Assert.assertEquals(1,count);
    }

    @Test
    public void listAll() {
        String association = rightsService.listAll(1,10).getList().get(0).getAssociationName();
        Assert.assertEquals("羽毛球协会",association);
    }

    @Test
    public void listByAssociation() {
        String association = rightsService.listByAssociation(1,1,10).getList().get(0).getAssociationName();
        Assert.assertEquals("羽毛球协会",association);
    }

    @Test
    public void listByStatus() {
        String associationName = rightsService.listByStatus(1,1,1,10).getList().get(0).getAssociationName();
        Assert.assertEquals("羽毛球协会", associationName);
    }

    @Test
    public void update() {
        int count = rightsService.update(1,1,1,"null");
        Assert.assertEquals(1,count);
    }
}