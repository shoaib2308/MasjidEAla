package simplycodighub.masjid_e_ala.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttendTrackActivityResponse {

    @SerializedName("std_id")
    @Expose
    private String stdId;
    @SerializedName("sep_count")
    @Expose
    private String sepCount;
    @SerializedName("oct_count")
    @Expose
    private String octCount;
    @SerializedName("nov_count")
    @Expose
    private String novCount;

    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
    }

    public String getSepCount() {
        return sepCount;
    }

    public void setSepCount(String sepCount) {
        this.sepCount = sepCount;
    }

    public String getOctCount() {
        return octCount;
    }

    public void setOctCount(String octCount) {
        this.octCount = octCount;
    }

    public String getNovCount() {
        return novCount;
    }

    public void setNovCount(String novCount) {
        this.novCount = novCount;
    }

}