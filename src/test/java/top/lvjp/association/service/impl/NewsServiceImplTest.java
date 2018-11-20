package top.lvjp.association.service.impl;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.lvjp.association.VO.NewsInfo;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.form.NewsForm;
import top.lvjp.association.service.NewsService;

@SpringBootTest
@MapperScan("top.lvjp.association.mapper")
@RunWith(SpringRunner.class)
public class NewsServiceImplTest {

    @Autowired
    private NewsService newsService;

    @Test
    public void selectByStatus() {
        PageVO<NewsInfo> newsInfoPageVO = newsService.selectByStatus(1,1,1,10);
        Assert.assertEquals(new Integer(1),newsInfoPageVO.getList().get(0).getNewsId());
    }

    @Ignore
    public void queryByKey() {
        PageVO<NewsInfo> vo = newsService.queryByKey("羽毛",0,1,10);
        Assert.assertEquals(new Integer(1),vo.getList().get(0).getNewsId());
    }

    @Test
    public void publish() {
        Assert.assertEquals(1,newsService.publish(2,1));
    }

    @Test
    public void update() {
        NewsForm newsForm = new NewsForm();
        newsForm.setNewsId(2);
        newsForm.setAssociationId(1);
        newsForm.setNewsAuthor("lvjip");
        newsForm.setNewsDigest("nonono");
        newsForm.setNewsImage("////");
        newsForm.setNewsStatus(0);
        newsForm.setNewsTitle("title");
        newsForm.setNewsText("text");
        newsService.update(newsForm);
    }

    @Ignore
    public void insert() {
        NewsForm newsForm = new NewsForm();
        newsForm.setAssociationId(1);
        newsForm.setNewsAuthor("lvjip");
        newsForm.setNewsDigest("nonono");
        newsForm.setNewsImage("////");
        newsForm.setNewsStatus(1);
        newsForm.setNewsTitle("title");
        newsForm.setNewsText("text");
        Assert.assertEquals(1,newsService.insert(newsForm));
    }

    @Test
    public void delete() {
        Assert.assertEquals(1,newsService.delete(2,1));
    }
}