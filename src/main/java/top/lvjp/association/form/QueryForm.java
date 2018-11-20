package top.lvjp.association.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class QueryForm {

    private String studentName;

    private String studentNumber;

    private String academy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date minTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date maxTime;

    private Integer sex;

    private Integer campus;
}
