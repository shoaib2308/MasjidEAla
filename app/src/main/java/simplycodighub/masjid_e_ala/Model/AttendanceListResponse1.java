package simplycodighub.masjid_e_ala.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttendanceListResponse1 {
    @SerializedName("std_id")
    @Expose
    private String stdId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("shift")
    @Expose
    private String shift;
    @SerializedName("student_type")
    @Expose
    private String studentType;
    @SerializedName("attend_status")
    @Expose
    private String attendStatus;
    @SerializedName("curr_day_p")
    @Expose
    private String currDayP;
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
    @SerializedName("fees")
    @Expose
    private String fees;
    @SerializedName("fees_status")
    @Expose
    private String feesStatus;
    @SerializedName("section_class")
    @Expose
    private String sectionClass;
    @SerializedName("doj")
    @Expose
    private String doj;
    @SerializedName("alt_mobile")
    @Expose
    private String altMobile;
    @SerializedName("course")
    @Expose
    private String course;

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

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public String getAttendStatus() {
        return attendStatus;
    }

    public void setAttendStatus(String attendStatus) {
        this.attendStatus = attendStatus;
    }

    public String getCurrDayP() {
        return currDayP;
    }

    public void setCurrDayP(String currDayP) {
        this.currDayP = currDayP;
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

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getFeesStatus() {
        return feesStatus;
    }

    public void setFeesStatus(String feesStatus) {
        this.feesStatus = feesStatus;
    }

    public String getSectionClass() {
        return sectionClass;
    }

    public void setSectionClass(String sectionClass) {
        this.sectionClass = sectionClass;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getAltMobile() {
        return altMobile;
    }

    public void setAltMobile(String altMobile) {
        this.altMobile = altMobile;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
