package simplycodighub.masjid_e_ala.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttendResponse {
    @SerializedName("status") // @serialized name same as server name
    @Expose
    private String status;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
