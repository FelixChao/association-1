package top.lvjp.association.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.lvjp.association.form.AssociationApplyForm;
import top.lvjp.association.service.AssociationApplyService;

@SpringBootTest
@RunWith(SpringRunner.class)
@MapperScan("top.lvjp.association.mapper")
public class AssociationApplyServiceImplTest {

    @Autowired
    private AssociationApplyService associationApplyService;

    @Test
    public void insertApply() {
        AssociationApplyForm associationApply = new AssociationApplyForm();
        associationApply.setAssociationId(20);
        associationApply.setStudentName("张三");
        associationApply.setStudentNumber("2016123123");
        associationApply.setAcademy("guanlixueyaun");
        associationApply.setApplyReason("reason");
        associationApply.setCampus(1);
        associationApply.setPhone("12312341234");
        associationApply.setQq("1231231234");
        associationApply.setSex(1);
        associationApply.setSpeciality("speciality");
        associationApplyService.insertApply(associationApply);
        Assert.assertEquals(1,1);
    }
}