package simplycodighub.masjid_e_ala.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import simplycodighub.masjid_e_ala.Interface.IRestService;
import simplycodighub.masjid_e_ala.Model.AttendResponse;
import simplycodighub.masjid_e_ala.Model.AttendanceListResponse1;
import simplycodighub.masjid_e_ala.R;
import simplycodighub.masjid_e_ala.Util.ApiClient;
import simplycodighub.masjid_e_ala.View.ProfileActivity;
import simplycodighub.masjid_e_ala.View.ShiftOneActivity;

public class Shift1Adapter extends  RecyclerView.Adapter<Shift1Adapter.MyViewHolder> implements Filterable {
    List<AttendanceListResponse1> attendanceListResponses;
    Context context;


    // SEARCH VIEW FILTER **************************************************************************
    private List<AttendanceListResponse1> al_searchlist;

    IRestService service;

    public Shift1Adapter(ShiftOneActivity shiftOneActivity, List<AttendanceListResponse1> attendanceListResponses) {
        this.context=shiftOneActivity;
        this.attendanceListResponses=attendanceListResponses;

        // SEARCH VIEW FILTER **************************************************************************
        this.al_searchlist = new ArrayList<AttendanceListResponse1>();
        al_searchlist.addAll(attendanceListResponses);
    }

    public void setNameList(Context context,final List<AttendanceListResponse1> mArrayList){
        this.context = context;

// CODE FOR SEARCH VIEW FILTER *********************************************************************
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return Shift1Adapter.this.al_searchlist.size();
            }

            @Override
            public int getNewListSize() {
                return mArrayList.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return Shift1Adapter.this.al_searchlist.get(oldItemPosition).getName() == mArrayList.get(newItemPosition).getName();
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                AttendanceListResponse1 newMovie = Shift1Adapter.this.al_searchlist.get(oldItemPosition);

                AttendanceListResponse1 oldMovie = mArrayList.get(newItemPosition);

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
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attendance_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final Shift1Adapter.MyViewHolder holder, int position) {


        final AttendanceListResponse1 attendanceListResponse = attendanceListResponses.get(position);

        holder.student_name.setText(attendanceListResponse.getName());
        holder.std_id.setText(attendanceListResponse.getStdId());
        holder.student_shift.setText(attendanceListResponse.getShift());
        holder.student_type.setText(attendanceListResponse.getStudentType());

        String student_type = attendanceListResponse.getStudentType();


        holder.edit_stdid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Dismissed..!!   came ",Toast.LENGTH_SHORT).show();

               Intent i = new Intent(context,ProfileActivity.class);
                i.putExtra("std_id",attendanceListResponse.getStdId());
                i.putExtra("name",attendanceListResponse.getName());
                i.putExtra("father_name",attendanceListResponse.getFatherName());
                i.putExtra("father_num",attendanceListResponse.getFatherMobile());
                i.putExtra("address",attendanceListResponse.getAddress());
                i.putExtra("gender",attendanceListResponse.getGender());
                i.putExtra("course",attendanceListResponse.getCourse());
                i.putExtra("fees",attendanceListResponse.getFees());


                i.putExtra("student_type",attendanceListResponse.getStudentType());
                i.putExtra("fees_status",attendanceListResponse.getFeesStatus());
                i.putExtra("alt_mobile",attendanceListResponse.getAltMobile());
                i.putExtra("classy",attendanceListResponse.getSectionClass());
                i.putExtra("shift",attendanceListResponse.getShift());
                i.putExtra("doj",attendanceListResponse.getDoj());


                context.startActivity(i);

            }
        });


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
                holder.img_present.setVisibility(View.INVISIBLE);
                holder.img_absent.setVisibility(View.INVISIBLE);
                holder.img_status_absent.setVisibility(View.VISIBLE);


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

                    private void Send(String s1, String s2) {

                        //  Toast.makeText(context,"Dismissed..!!"+attendanceListResponse.getStdId(),Toast.LENGTH_SHORT).show();
                        service = ApiClient.getRetrofit().create(IRestService.class);
                        service.getAttend(s1,s2).enqueue(new Callback<AttendResponse>() {
                            @Override
                            public void onResponse(Call<AttendResponse> call, Response<AttendResponse> response) {

                                String status = response.body().getStatus();

                                if(status.equals("success")){

                                    Intent i = new Intent(context,ShiftOneActivity.class);
                                    context.startActivity(i);
                                    ((Activity)context).finish();  // BEST WAY FINISH FROm ADAPTER

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
                dialog.show();*/

            }

            private void Send(String s1, String s2) {

                //  Toast.makeText(context,"Dismissed..!!"+attendanceListResponse.getStdId(),Toast.LENGTH_SHORT).show();
                service = ApiClient.getRetrofit().create(IRestService.class);
                service.getAttend(s1,s2).enqueue(new Callback<AttendResponse>() {
                    @Override
                    public void onResponse(Call<AttendResponse> call, Response<AttendResponse> response) {

                        String status = response.body().getStatus();

                        if(status.equals("success")){


                            Toast.makeText(context,"Successfully submit "+status,Toast.LENGTH_SHORT).show();

                           // Intent i = new Intent(context,ShiftOneActivity.class);
                          //  context.startActivity(i);



                          //  ((Activity)context).finish();  // BEST WAY FINISH FROm ADAPTER



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

                Toast.makeText(context,"Assalaam Aleikum "+attendanceListResponse.getStdId(),Toast.LENGTH_LONG).show();

                String s1 = attendanceListResponse.getStdId();
                String s2 = "P";
                holder.img_present.setVisibility(View.INVISIBLE);
                holder.img_absent.setVisibility(View.INVISIBLE);
                holder.img_status.setVisibility(View.VISIBLE);

                Sendid(s1,s2);


            //   showing eror
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

                //  Toast.makeText(context,"Dismissed..!!"+attendanceListResponse.getStdId(),Toast.LENGTH_SHORT).show();
                service = ApiClient.getRetrofit().create(IRestService.class);
                service.getAttend(s1,s2).enqueue(new Callback<AttendResponse>() {
                    @Override
                    public void onResponse(Call<AttendResponse> call, Response<AttendResponse> response) {

                        String status = response.body().getStatus();

                        if(status.equals("success")){

                            Toast.makeText(context,"Perfectly Successfully submit "+status,Toast.LENGTH_SHORT).show();

                            // Intent i = new Intent(context,ShiftOneActivity.class);
                            //context.startActivity(i);
                            //((Activity)context).finish();  // BEST WAY FINISH FROm ADAPTER

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

                    List<AttendanceListResponse1> filteredList = new ArrayList<>();

                    for (AttendanceListResponse1 androidVersion : al_searchlist) {

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
                attendanceListResponses = (List<AttendanceListResponse1>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView student_name,std_id,tv_id,tv_name,student_shift,student_type,edit_stdid;

        ImageView img_present,img_status,img_absent,img_status_absent;
       ShimmerFrameLayout  mShimmerViewContainer;
        LinearLayout linear_bt1;
        public MyViewHolder(View itemView) {
            super(itemView);

            mShimmerViewContainer = itemView.findViewById(R.id.shimmer_view_container);
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

            edit_stdid = (TextView) itemView.findViewById(R.id.edit_stdid);


        }
    }


}
