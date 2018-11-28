package top.lvjp.association.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
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
     * @param file 要上传的文件
     * @param userId 上传用户
     * @param type 指定类型, 必须为 IMAGE_FILE 或 VIDEO_FILE
     * @return 返回上传的文件存储的位置和文件名, URI
     */
    public String uploadFile(MultipartFile file, Integer userId, int type){
        String path = null;
        if (file == null || file.isEmpty()) {
            throw new MyException(ResultEnum.FILE_IS_EMPTY);
        }
        String contentType = file.getContentType();
        String fileType = contentType.substring(contentType.indexOf("/") + 1).toUpperCase();
        if (fileType == null) {
            throw new MyException(ResultEnum.FILE_TYPE_ERROR);
        }
        if (type == IMAGE_FILE){
            String[] types = imageTypes.split(",");
            for (int i = 0; i < types.length; i++) {
                if (fileType.equals(types[i])){
                    path = imagePath;
                    break;
                }
            }
        } else if (type == VIDEO_FILE){
            String[] types = videoTypes.split(",");
            for (int i = 0; i < types.length; i++) {
                if (fileType.equals(types[i])){
                    path = videoPath;
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
            log.info("用户:{} 上传文件 {} 成功!",userId, fileName);
        } catch (IOException e) {
            log.error("用户:{} 上传文件 {} 失败!",userId, fileName);
            e.printStackTrace();
            throw new MyException(ResultEnum.FILL_UPLOAD_FAILED);
        }
        return dest.getPath();
    }
}

