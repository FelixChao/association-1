package top.lvjp.association.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.lvjp.association.VO.ActivityInfo;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.Result;
import top.lvjp.association.dto.ActivitiesDTO;
import top.lvjp.association.service.ActivityService;
import top.lvjp.association.util.ResultUtil;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 查询最近的活动
     * @return
     */
    @GetMapping("/latest")
    public Result selectLatest(@RequestParam(value = "count", required = false, defaultValue = "10") Integer count){
        List<ActivityInfo> activityInfos = activityService.selectLatest(count);
        return ResultUtil.success(activityInfos);
    }

    /**
     * 查询所有的活动
     * @return
     */
    @GetMapping("/list")
    public Result list(){
        ActivitiesDTO activitiesDTO = activityService.listAll();
        return ResultUtil.success(activitiesDTO);
    }

    /**
     * 分页查询正在进行的活动
     * @return
     */
    @GetMapping("/current")
    public Result selectCurrent(@RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size){
        PageVO<ActivityInfo> current = activityService.selectCurrent(pageNum, size);
        return ResultUtil.success(current);
    }

    /**
     * 分页查询即将开始的活动
     * @return
     */
    @GetMapping("/future")
    public Result selectFuture(@RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size){
        PageVO<ActivityInfo> future = activityService.selectFuture(pageNum, size);
        return ResultUtil.success(future);
    }

    /**
     * 分页查询已经结束的活动
     * @return
     */
    @GetMapping("/past")
    public Result selectPast(@RequestParam("pageNum") Integer pageNum, @RequestParam("size") Integer size){
        PageVO<ActivityInfo> past = activityService.selectPast(pageNum, size);
        return ResultUtil.success(past);
    }

    /**
     * 查询某个活动详细信息
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Result selectById(@RequestParam("id") Integer id){
        ActivityInfo activityInfo = activityService.getInfoById(id);
        return ResultUtil.success(activityInfo);
    }

}
