package top.lvjp.association.entity;

import java.util.Date;

public class AssociationApply {


    /**
     * 申请编号
     */
    private Integer applyId;

    /**
     * 申请社团编号
     */
    private Integer associationId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 学号
     */
    private Integer studentNumbe;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 电话
     */
    private String phone;

    /**
     * QQ
     */
    private String qq;

    /**
     * 所在校区
     */
    private Integer campus;

    /**
     * 学生所在学院
     */
    private String academy;

    /**
     * 学生专业
     */
    private String speciality;

    /**
     * 申请时间
     */
    private Date applyTime;

    /**
     * 申请理由
     */
    private String applyReason;

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Integer getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Integer associationId) {
        this.associationId = associationId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentNumbe() {
        return studentNumbe;
    }

    public void setStudentNumbe(Integer studentNumbe) {
        this.studentNumbe = studentNumbe;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Integer getCampus() {
        return campus;
    }

    public void setCampus(Integer campus) {
        this.campus = campus;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }
}
