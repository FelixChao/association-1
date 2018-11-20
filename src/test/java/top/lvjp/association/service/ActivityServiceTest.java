package top.lvjp.association.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ActivityServiceTest {

    @Autowired
    private ActivityService activityService;

    @Ignore
    public void delete() {
        boolean success = activityService.delete(4,1);
        Assert.assertEquals(1,success);
    }
}