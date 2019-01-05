package top.lvjp.association.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.lvjp.association.config.MyWebMvcConfigurer;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.exception.MyException;

import java.io.File;
import java.io.IOException;

@Slf4j
@Component
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "upload")
public class FileUtil {

//    private static final String imagePath  = "D:/mingliyuan/associationFile/file/img";
//    private static final String videoPath  = "D:/mingliyuan/associationFile/file/video";
//
//    private static final String imageTypes  =  "PNG,JPG,GIF,JPEG";
//    private static final String videoTypes  =  "MP4";

    @Value("${image-path}")
    private String imagePath;
    @Value("${video-path}")
    private String videoPath;

    @Value("${image-types}")
    private String imageTypes;
    @Value("${video-types}")
    private String videoTypes;

    public static final int VIDEO_FILE = 1;
    public static final int IMAGE_FILE = 2;

    /**
     * 根据指定类型, 上传视频或图片文件
     * 文件储存路径上一级为社团 id
     * @param file 要上传的文件
     * @param associationId 上传用户所属社团
     * @param type 指定类型, 必须为 IMAGE_FILE 或 VIDEO_FILE
     * @return 返回上传的文件的访问地址 ( 自定义资源路径 + 文件名 )
     */
    public String uploadFile(MultipartFile file, String associationId, int type){
        String path = null;
        String accessPath = null;
        String associationPath = associationId + "/";
        if (file == null || file.isEmpty()) {
            throw new MyException(ResultEnum.FILE_IS_EMPTY);
        }
        String contentType = file.getContentType();
        String fileType = contentType.substring(contentType.indexOf("/") + 1).toUpperCase();
        if (fileType.isEmpty()) {
            throw new MyException(ResultEnum.FILE_TYPE_ERROR);
        }
        if (type == IMAGE_FILE){
            String[] types = imageTypes.split(",");
            for (String type1 : types) {
                if (fileType.equals(type1)) {
                    path = imagePath + associationPath;
                    accessPath = MyWebMvcConfigurer.IMAGE_ACCESS_PATH + associationPath;
                    break;
                }
            }
        } else if (type == VIDEO_FILE){
            String[] types = videoTypes.split(",");
            for (String type1 : types) {
                if (fileType.equals(type1)) {
                    path = videoPath + associationPath;
                    accessPath = MyWebMvcConfigurer.VIDEO_ACCESS_PATH + associationPath;
                    break;
                }
            }
        } else throw new IllegalArgumentException("参数type只能为 FileUtil.VIDEO_FILE 或 FileUtil.IMAGE_FILE");
        if (path == null) {
            throw new MyException(ResultEnum.FILE_TYPE_ERROR);
        }
        String fileName = file.getOriginalFilename();
        File dest = new File(path + fileName);
        if (!dest.getParentFile().exists()){
            log.warn("上传文件目录 {} 不存在!",dest.getParentFile());
            if (dest.getParentFile().mkdirs()){
                log.info("目录创建成功");
            } else {
                log.error("目录创建失败");
            }
        }
        try {
            file.transferTo(dest);
            log.info("上传文件 {} 成功!", fileName);
        } catch (IOException e) {
            log.error("上传文件 {} 失败!", fileName);
            e.printStackTrace();
            throw new MyException(ResultEnum.FILL_UPLOAD_FAILED);
        }
        return accessPath + fileName;
    }

    public void deleteFile(String filePath, int type) {
        String fileName = filePath.substring(filePath.indexOf("/",2) + 1);
        if (type == IMAGE_FILE) {
            filePath = imagePath + fileName;
        } else if (type == VIDEO_FILE) {
            filePath = videoPath + fileName;
        } else throw new IllegalArgumentException("参数type只能为 FileUtil.VIDEO_FILE 或 FileUtil.IMAGE_FILE");
        File file = new File(filePath);
        if (file.delete()){
            log.info("成功删除 {} 文件", filePath);
        }
    }
}