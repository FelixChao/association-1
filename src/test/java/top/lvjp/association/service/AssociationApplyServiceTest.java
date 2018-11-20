package top.lvjp.association.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.entity.AssociationApply;
import top.lvjp.association.form.AssociationApplyForm;
import top.lvjp.association.form.QueryForm;

@Ignore
@SpringBootTest
@RunWith(SpringRunner.class)
public class AssociationApplyServiceTest {

    @Autowired
    private AssociationApplyService associationApplyService;

    private static int number = 2016300000;

    @Test
    public void insertApply(){
        AssociationApplyForm form = new AssociationApplyForm();
        form.setAssociationId(3);
        form.setAcademy("管理学院");
        form.setCampus(1);
        form.setStudentName("lvjp");
        form.setSpeciality("xinguan");
        form.setSex(1);
        form.setReason("love");
        form.setQq("12345666");
        form.setPhone("13778789876");
        form.setDepartment("jishubu");
        for (int i = 0; i < 50; i++) {
            number++;
            form.setStudentNumber(String.valueOf(number));
            associationApplyService.insertApply(form);
        }
    }

    @Test
    public void listByAssociation() {
        PageVO<AssociationApply> vo = associationApplyService.listByAssociation(1,1,10);
        System.out.println(vo.getList().get(0).getStudentName());
        Assert.assertEquals(2,vo.getTotals());
    }

    @Test
    public void query() {
        QueryForm form = new QueryForm();
        form.setStudentName("李");
        form.setAcademy("lixue");
        form.setCampus(1);
        form.setSex(1);
        PageVO<AssociationApply> vo = associationApplyService.query(form,1,1,10);
        Assert.assertEquals("李四",vo.getList().get(0).getStudentName());
    }

    @Test
    public void delete() {
        int count = associationApplyService.delete(2);
        Assert.assertEquals(2,count);
    }
}