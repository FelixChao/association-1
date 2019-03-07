package top.lvjp.association.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class VideoForm {

    private Integer videoId;

//    private String associationId;
//
//    private Integer userId;

//    @NotNull(message = "请选择上传文件")
    private MultipartFile file;

    @NotBlank(message = "请输入标题!")
    private String title;

    private String desc;

}
