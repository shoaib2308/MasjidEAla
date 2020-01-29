package simplycodighub.masjid_e_ala.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentListResponse {
    @SerializedName("std_id")
    @Expose
    private String stdId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("father_name")
    @Expose
    private String fatherName;
    @SerializedName("father_mobile")
    @Expose
    private String fatherMobile;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("course")
    @Expose
    private String course;

    @SerializedName("fees")
    @Expose
    private String fees;

    @SerializedName("section_class")
    @Expose
    private String section_class;


    @SerializedName("fees_status")
    @Expose
    private String fees_status;

    @SerializedName("alt_mobile")
    @Expose
    private String alt_mobile;

    @SerializedName("shift")
    @Expose
    private String shift;

    @SerializedName("doj")
    @Expose
    private String doj;

    @SerializedName("student_type")
    @Expose
    private String student_type;

    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherMobile() {
        return fatherMobile;
    }

    public void setFatherMobile(String fatherMobile) {
        this.fatherMobile = fatherMobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getSection_class() {
        return section_class;
    }

    public void setSection_class(String section_class) {
        this.section_class = section_class;
    }

    public String getFees_status() {
        return fees_status;
    }

    public void setFees_status(String fees_status) {
        this.fees_status = fees_status;
    }

    public String getAlt_mobile() {
        return alt_mobile;
    }

    public void setAlt_mobile(String alt_mobile) {
        this.alt_mobile = alt_mobile;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getStudent_type() {
        return student_type;
    }

    public void setStudent_type(String student_type) {
        this.student_type = student_type;
    }
}
