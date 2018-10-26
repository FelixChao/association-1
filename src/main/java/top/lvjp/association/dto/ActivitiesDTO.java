package top.lvjp.association.dto;

import lombok.Data;
import top.lvjp.association.VO.ActivityInfo;

import java.util.List;

@Data
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

}
