package simplycodighub.masjid_e_ala.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import simplycodighub.masjid_e_ala.Interface.IRestService;
import simplycodighub.masjid_e_ala.Model.AttendTrackActivityResponse;
import simplycodighub.masjid_e_ala.R;
import simplycodighub.masjid_e_ala.View.AttendTrackActivity;

public class AttendTrackActivityAdapter extends RecyclerView.Adapter<AttendTrackActivityAdapter.MyViewHolder> {
    List<AttendTrackActivityResponse> attendanceListResponses = new ArrayList<>();
    Context context;
    IRestService service;



    public AttendTrackActivityAdapter(AttendTrackActivity attendTrackActivity, List<AttendTrackActivityResponse> responseList) {

        this.context=attendTrackActivity;
        this.attendanceListResponses=responseList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row4, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendTrackActivityAdapter.MyViewHolder holder, int position) {

        final AttendTrackActivityResponse attendanceListResponse = attendanceListResponses.get(position);

        holder.std_id.setText(attendanceListResponse.getStdId());
        holder.sep_count.setText(attendanceListResponse.getSepCount());
        holder.oct_count.setText(attendanceListResponse.getOctCount());
        holder.nov_count.setText(attendanceListResponse.getNovCount());

        // String nov_count = attendanceListResponse.getNovCount();
    }



    @Override
    public int getItemCount() {
        return attendanceListResponses.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{
        TextView sep_count,std_id,oct_count,nov_count;
        public MyViewHolder(View itemView) {
            super(itemView);

            std_id = (TextView) itemView.findViewById(R.id.std_id);

            sep_count = (TextView) itemView.findViewById(R.id.sep_count);

            oct_count = (TextView) itemView.findViewById(R.id.oct_count);

            nov_count = (TextView) itemView.findViewById(R.id.nov_count);
        }
    }
}
