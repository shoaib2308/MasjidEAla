package simplycodighub.masjid_e_ala.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttendanceListResponse3 {
    @SerializedName("std_id")
    @Expose
    private String stdId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("shift")
    @Expose
    private String shift;
    @SerializedName("attend_status")
    @Expose
    private String attendStatus;
    @SerializedName("course")
    @Expose
    private String course;



    @SerializedName("student_type")
    @Expose
    private String student_type;

    @SerializedName("curr_day_p")
    @Expose
    private String curr_day_p;


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

    public String getAttendStatus() {
        return attendStatus;
    }

    public void setAttendStatus(String attendStatus) {
        this.attendStatus = attendStatus;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getStudent_type() {
        return student_type;
    }

    public String getCurr_day_p() {
        return curr_day_p;
    }

    public void setCurr_day_p(String curr_day_p) {
        this.curr_day_p = curr_day_p;
    }
}
