package top.lvjp.association.dto;

import top.lvjp.association.VO.ActivityInfo;

import java.util.List;

public class ActivitiesDTO {

    /**
     * 正在进行的活动
     */
    private List<ActivityInfo> current;

    /**
     * 即将开始的活动
     */
    private List<ActivityInfo> future;

    /**
     * 已经结束的活动
     */
    private List<ActivityInfo> past;

    public ActivitiesDTO() {
    }

    public ActivitiesDTO(List<ActivityInfo> current, List<ActivityInfo> future, List<ActivityInfo> past) {
        this.current = current;
        this.future = future;
        this.past = past;
    }

    public List<ActivityInfo> getCurrent() {
        return current;
    }

    public void setCurrent(List<ActivityInfo> current) {
        this.current = current;
    }

    public List<ActivityInfo> getFuture() {
        return future;
    }

    public void setFuture(List<ActivityInfo> future) {
        this.future = future;
    }

    public List<ActivityInfo> getPast() {
        return past;
    }

    public void setPast(List<ActivityInfo> past) {
        this.past = past;
    }
}
