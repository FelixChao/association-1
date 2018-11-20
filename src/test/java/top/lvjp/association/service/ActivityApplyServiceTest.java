package top.lvjp.association.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.entity.ActivityApply;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ActivityApplyServiceTest {

    @Autowired
    private ActivityApplyService activityApplyService;

    @Test
    public void selectByActivity() {
        PageVO<ActivityApply> activityApplyPageVO = activityApplyService.selectByActivity(1,3,2,10);
        Assert.assertEquals(200,activityApplyPageVO.getTotals());
    }

    @Ignore
    public void query() {
//        QueryForm queryForm = new QueryForm();
//        queryForm.setMaxTime(n);
    }
}