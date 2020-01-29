package simplycodighub.masjid_e_ala.Adapter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import simplycodighub.masjid_e_ala.Model.AttendanceListResponse5;
import simplycodighub.masjid_e_ala.R;
import simplycodighub.masjid_e_ala.Util.ApiClient;
import simplycodighub.masjid_e_ala.Util.ProgressDialogHandler;
import simplycodighub.masjid_e_ala.View.AttendanceActivity;
import simplycodighub.masjid_e_ala.View.ShiftFiveActivity;
import simplycodighub.masjid_e_ala.View.ShiftOneActivity;
import simplycodighub.masjid_e_ala.View.ShiftTwoActivity;

public class Shift5Adapter extends  RecyclerView.Adapter<Shift5Adapter.MyViewHolder> implements Filterable {
    List<AttendanceListResponse5> attendanceListResponses = new ArrayList<>();
    private List<AttendanceListResponse5> list2= new ArrayList<>();
    Context context;
    private static ProgressDialog progressDialog = null;

    private ArrayList<AttendanceListResponse5> categoriesList;
     ProgressBar mprogressBar;
    // SEARCH VIEW FILTER **************************************************************************
    private List<AttendanceListResponse5> al_searchlist;
    IRestService service;
    ShiftFiveActivity activity;

    public Shift5Adapter(ShiftFiveActivity shiftFiveActivity, List<AttendanceListResponse5> attendanceListResponses) {
        this.context=shiftFiveActivity;
        this.attendanceListResponses=attendanceListResponses;
        this.list2=attendanceListResponses;
        notifyDataSetChanged();
        // SEARCH VIEW FILTER **************************************************************************
        this.al_searchlist = new ArrayList<AttendanceListResponse5>();
        al_searchlist.addAll(attendanceListResponses);
    }

    public void swap(ArrayList<AttendanceListResponse5> datas) {
        if (datas == null || datas.size() == 0)
            list2.clear();
        list2.addAll(datas);
        this.notifyDataSetChanged();
        if (list2 != null && list2.size() > 0)
            list2.clear();
        list2.addAll(datas);
        this.notifyDataSetChanged();

    }

    public void setNameList(Context context,final List<AttendanceListResponse5> mArrayList){
        this.context = context;

// CODE FOR SEARCH VIEW FILTER *********************************************************************
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return Shift5Adapter.this.al_searchlist.size();
            }

            @Override
            public int getNewListSize() {
                return mArrayList.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return Shift5Adapter.this.al_searchlist.get(oldItemPosition).getName() == mArrayList.get(newItemPosition).getName();
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                AttendanceListResponse5 newMovie = Shift5Adapter.this.al_searchlist.get(oldItemPosition);

                AttendanceListResponse5 oldMovie = mArrayList.get(newItemPosition);

                return newMovie.getName() == oldMovie.getName() ;
            }
        });

        this.al_searchlist = mArrayList;
        this.attendanceListResponses = mArrayList;
        result.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // itemView = LayoutInflater.from(context).inflate(R.layout.attendance_row, parent, false);
        // final MyViewHolder vHolder = new MyViewHolder(v);
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attendance_row, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final Shift5Adapter.MyViewHolder holder, int position) {


        final AttendanceListResponse5 attendanceListResponse = attendanceListResponses.get(position);

        holder.student_name.setText(attendanceListResponse.getName());
        holder.std_id.setText(attendanceListResponse.getStdId());
        holder.student_shift.setText(attendanceListResponse.getShift());
        holder.student_type.setText(attendanceListResponse.getStudent_type());

        String student_type = attendanceListResponse.getStudent_type();

        if(student_type.equals("Deeniyat")){

            holder.student_type.setBackgroundResource(R.drawable.button_bg2);


        }
        else if(student_type.equals("Balighaan")){

            holder.student_type.setBackgroundResource(R.drawable.button_bg1);

        }

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
                Send(s1,s2);

              /*  final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.alertdailog_absent);
                TextView tv_id1 = (TextView) dialog.findViewById(R.id.tv_id1);
                TextView tv_name1 = (TextView) dialog.findViewById(R.id.tv_name1);

                Button dialogButton = (Button) dialog.findViewById(R.id.btn_submit1);

                tv_id1.setText(attendanceListResponse.getStdId());
                tv_name1.setText(attendanceListResponse.getName());
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //dialog.dismiss();

                        String s1 = attendanceListResponse.getStdId();
                        String s2 = "A";
                        Toast.makeText(context,"Successfully submit "+s1,Toast.LENGTH_SHORT).show();

                        Send(s1,s2);
                        //dialog.dismiss();
                    }


                });
                dialog.show();*/

            }


            private void Send(String s1, String s2) {
                mprogressBar.setVisibility(View.VISIBLE);
                //  Toast.makeText(context,"Dismissed..!!"+attendanceListResponse.getStdId(),Toast.LENGTH_SHORT).show();
                service = ApiClient.getRetrofit().create(IRestService.class);
                service.getAttend(s1,s2).enqueue(new Callback<AttendResponse>() {
                    @Override
                    public void onResponse(Call<AttendResponse> call, Response<AttendResponse> response) {

                        String status = response.body().getStatus();

                        if(status.equals("success")){
                            ProgressDialogHandler.hideBusyScreen();
                            mprogressBar.setVisibility(View.GONE);

                            getList();
                          //  Toast.makeText(context,"Successfully submit "+status,Toast.LENGTH_SHORT).show();

                          //  Intent i = new Intent(context,ShiftFiveActivity.class);
                          //  context.startActivity(i);
                          //  ((Activity)context).finish();  // BEST WAY FINISH FROm ADAPTER

                        }
                        else{

                            Toast.makeText(context,"Not done",Toast.LENGTH_LONG).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<AttendResponse> call, Throwable t) {
                        ProgressDialogHandler.hideBusyScreen();
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
                Sendid(s1,s2);

              /*  final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.alertdailog_attendance);
                TextView tv_id = (TextView) dialog.findViewById(R.id.tv_id);
                TextView tv_name = (TextView) dialog.findViewById(R.id.tv_name);

                Button dialogButton = (Button) dialog.findViewById(R.id.btn_submit);

                tv_id.setText(attendanceListResponse.getStdId());
                tv_name.setText(attendanceListResponse.getName());
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //dialog.dismiss();

                        String s1 = attendanceListResponse.getStdId();
                        String s2 = "P";
                        Toast.makeText(context,"Successfully submit "+s1,Toast.LENGTH_SHORT).show();

                        Sendid(s1,s2);
                        //dialog.dismiss();
                    }
                });
                dialog.show();*/


                //  Intent i = new Intent(context,ProfileActivity.class);
                // i.putExtra("std_id",attendanceListResponse.getStdId());
                //   i.putExtra("name",attendanceListResponse.getName());


                // context.startActivity(i);

            }


            private void Sendid(String s1,String s2) {
                mprogressBar.setVisibility(View.VISIBLE);
                //  Toast.makeText(context,"Dismissed..!!"+attendanceListResponse.getStdId(),Toast.LENGTH_SHORT).show();
                service = ApiClient.getRetrofit().create(IRestService.class);
                service.getAttend(s1,s2).enqueue(new Callback<AttendResponse>() {
                    @Override
                    public void onResponse(Call<AttendResponse> call, Response<AttendResponse> response) {

                        String status = response.body().getStatus();

                        if(status.equals("success")){
                            mprogressBar.setVisibility(View.GONE);
                            getList();
                          //  Toast.makeText(context,"Successfully submit "+status,Toast.LENGTH_SHORT).show();

                          //  Intent i = new Intent(context,ShiftFiveActivity.class);
                          //  context.startActivity(i);
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



    }


    // CODE FOR SEARCH VIEW FILTER *********************************************************************
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return attendanceListResponses.size();
    }
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    attendanceListResponses = al_searchlist;
                } else {

                    List<AttendanceListResponse5> filteredList = new ArrayList<>();

                    for (AttendanceListResponse5 androidVersion : al_searchlist) {

                        if (androidVersion.getName().toLowerCase().contains(charString) || androidVersion.getStdId().toLowerCase().contains(charString)) {

                            filteredList.add(androidVersion);
                        }
                    }

                    attendanceListResponses = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = attendanceListResponses;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                attendanceListResponses = (List<AttendanceListResponse5>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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

            mprogressBar = (ProgressBar) itemView.findViewById(R.id.pBar);



        }
    }

    private void getList() {

        service = ApiClient.getRetrofit().create(IRestService.class);
        service.getAttendanceList5().enqueue(new Callback<List<AttendanceListResponse5>>() {
            @Override
            public void onResponse(Call<List<AttendanceListResponse5>> call, Response<List<AttendanceListResponse5>> response) {

                //asyncDialog.hide();
                Log.d("jjjjjjjjjjj","jjjjjjjjjjjj");
                //swipeRefreshLayout.setRefreshing(false);
                categoriesList = new ArrayList<AttendanceListResponse5>();
                categoriesList.addAll(response.body());
                swap(categoriesList);
            }

            @Override
            public void onFailure(Call<List<AttendanceListResponse5>> call, Throwable t) {

            }
        });


    }



}
