package top.lvjp.association.VO;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

@Data
public class PageVO<T> {

    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 记录总条数
     */
    private long totals;

    /**
     * 总页数
     */
    private int pages;

    /**
     * 每页显示的数据
     */
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public PageVO(PageInfo<T> pageInfo){
        list = pageInfo.getList();
        totals = pageInfo.getTotal();
        pages = pageInfo.getPages();
        pageNum = pageInfo.getPageNum();
    }
}
