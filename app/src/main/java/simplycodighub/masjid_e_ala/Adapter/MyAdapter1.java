package simplycodighub.masjid_e_ala.Adapter;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import simplycodighub.masjid_e_ala.Interface.IRestService;
import simplycodighub.masjid_e_ala.Model.AttendResponse;
import simplycodighub.masjid_e_ala.Model.AttendanceListResponse1;
import simplycodighub.masjid_e_ala.Model.AttendanceListResponse2;
import simplycodighub.masjid_e_ala.R;
import simplycodighub.masjid_e_ala.Util.ApiClient;
import simplycodighub.masjid_e_ala.View.AttendanceActivity;
import simplycodighub.masjid_e_ala.View.Tab1;
import simplycodighub.masjid_e_ala.View.Tab2;

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder> {

    private List<AttendanceListResponse1> list= new ArrayList<>();
    private List<AttendanceListResponse1> list2= new ArrayList<>();

    Tab2 tab1;
    Context context;
    IRestService service;
    private MyAdapter1.CallBack mCallBack;

    private ArrayList<AttendanceListResponse1> categoriesList;

    public MyAdapter1 (MyAdapter1.CallBack callback){
        mCallBack = callback;
    }

    public MyAdapter1(List<AttendanceListResponse1> xyz,Tab1 tab2) {
        this.list = xyz;
        this.tab1=tab1;
        this.list2=xyz;
        notifyDataSetChanged();
    }


    public void swap(ArrayList<AttendanceListResponse1> datas) {
        if (datas == null || datas.size() == 0)
            list.clear();
        list.addAll(datas);
        this.notifyDataSetChanged();
        if (list != null && list.size() > 0)
            list.clear();
        list.addAll(datas);
        this.notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row3, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        final AttendanceListResponse1 attendanceListResponse = list.get(position);

        holder.student_name.setText(attendanceListResponse.getName());
        holder.std_id.setText(attendanceListResponse.getStdId());
        holder.student_shift.setText(attendanceListResponse.getShift());
        holder.student_type.setText(attendanceListResponse.getStudentType());

        String student_type = attendanceListResponse.getStudentType();


        String emp_p = attendanceListResponse.getAttendStatus();

        if(emp_p.equals("P")){

            holder.linear_bt1.setVisibility(View.GONE);
            holder.img_status.setVisibility(View.VISIBLE);

        }

        else if(emp_p.equals("A")){

            holder.linear_bt1.setVisibility(View.GONE);
            holder.img_status_absent.setVisibility(View.VISIBLE);

        }
        else if(emp_p.equals("")){

            holder.linear_bt1.setVisibility(View.VISIBLE);
            holder.img_status_absent.setVisibility(View.GONE);
            holder.img_status.setVisibility(View.GONE);

        }


        holder.img_absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = attendanceListResponse.getStdId();
                String s2 = "A";
                //Toast.makeText(context,"Successfully submit "+s1,Toast.LENGTH_SHORT).show();

                Send(s1,s2);

            }

            private void Send(String s1, String s2) {

                //  Toast.makeText(context,"Dismissed..!!"+attendanceListResponse.getStdId(),Toast.LENGTH_SHORT).show();
                service = ApiClient.getRetrofit().create(IRestService.class);
                service.getAttend(s1,s2).enqueue(new Callback<AttendResponse>() {
                    @Override
                    public void onResponse(Call<AttendResponse> call, Response<AttendResponse> response) {

                        String status = response.body().getStatus();

                        if(status.equals("success")){

                            String s1 = attendanceListResponse.getStdId();
                            // Toast.makeText(context,"Successfully submit "+s1,Toast.LENGTH_SHORT).show();

                            getList();
                            //Intent i = new Intent(context,AttendanceActivity.class);
                            // context.startActivity(i);
                            // ((Activity)context).finish();  // BEST WAY FINISH FROm ADAPTER
                        }
                        else{

                            Toast.makeText(context,"Not done",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<AttendResponse> call, Throwable t) {

                    }
                });

            }
        });





        holder.img_present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(context,"Assalaam Aleikum "+attendanceListResponse.getStdId(),Toast.LENGTH_LONG).show();
                String s1 = attendanceListResponse.getStdId();
                String s2 = "P";
                //Toast.makeText(context, "Successfully submit " + s1, Toast.LENGTH_SHORT).show();

                Log.d("sqqqqqqqqqqq",""+s1);
                Sendid(s1, s2);

            }


            private void Sendid(String s1,String s2) {

                //  Toast.makeText(context,"Dismissed..!!"+attendanceListResponse.getStdId(),Toast.LENGTH_SHORT).show();
                service = ApiClient.getRetrofit().create(IRestService.class);
                service.getAttend(s1,s2).enqueue(new Callback<AttendResponse>() {
                    @Override
                    public void onResponse(Call<AttendResponse> call, Response<AttendResponse> response) {

                        String status = response.body().getStatus();

                        if(status.equals("success"))
                        {
                            //Call List again

                            //getList();

                        }
                        else{
                            Toast.makeText(context,"Not done",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<AttendResponse> call, Throwable t) {

                    }
                });
            }


        });



    }


    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView student_name,std_id,tv_id,tv_name,student_shift,student_type;

        ImageView img_present,img_status,img_absent,img_status_absent;

        LinearLayout linear_bt1;

        public MyViewHolder(View itemView) {
            super(itemView);

            student_name = (TextView) itemView.findViewById(R.id.student_name);

            std_id = (TextView) itemView.findViewById(R.id.std_id);

            img_present = (ImageView) itemView.findViewById(R.id.img_present);

            img_absent = (ImageView) itemView.findViewById(R.id.img_absent);

            img_status = (ImageView) itemView.findViewById(R.id.img_status);

            img_status_absent = (ImageView) itemView.findViewById(R.id.img_status_absent);

            tv_id = (TextView) itemView.findViewById(R.id.tv_id);

            tv_name = (TextView) itemView.findViewById(R.id.tv_name);

            student_shift = (TextView) itemView.findViewById(R.id.student_shift);

            student_type = (TextView) itemView.findViewById(R.id.student_type);

            linear_bt1 = (LinearLayout) itemView.findViewById(R.id.linear_bt1);

        }
    }

    public interface CallBack{
        void imageButtonOnClick();

    }

    private void getList() {

        service = ApiClient.getRetrofit().create(IRestService.class);
        service.getAttendanceList1().enqueue(new Callback<List<AttendanceListResponse1>>() {
            @Override
            public void onResponse(Call<List<AttendanceListResponse1>> call, Response<List<AttendanceListResponse1>> response) {

                //asyncDialog.hide();
                Log.d("jjjjjjjjjjj","jjjjjjjjjjjj");
                //swipeRefreshLayout.setRefreshing(false);
                categoriesList = new ArrayList<AttendanceListResponse1>();
                categoriesList.addAll(response.body());
                swap(categoriesList);
            }

            @Override
            public void onFailure(Call<List<AttendanceListResponse1>> call, Throwable t) {

            }
        });

    }

}
