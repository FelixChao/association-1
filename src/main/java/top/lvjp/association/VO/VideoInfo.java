package top.lvjp.association.VO;

public class VideoInfo {

    /**
     * 视频编号
     */
    private Integer videoId;

    /**
     * 视频标题
     */
    private String videoTitle;

    /**
     * 视频路径
     */
    private String videoPath;

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
}
