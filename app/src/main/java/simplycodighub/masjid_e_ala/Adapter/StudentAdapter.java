package simplycodighub.masjid_e_ala.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import simplycodighub.masjid_e_ala.Model.AttendanceListResponse1;
import simplycodighub.masjid_e_ala.Model.StudentListResponse;
import simplycodighub.masjid_e_ala.R;
import simplycodighub.masjid_e_ala.View.AllStudentActivity;
import simplycodighub.masjid_e_ala.View.ProfileActivity;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> implements Filterable {


    List<StudentListResponse> studentListResponses;
    Context context;

    // SEARCH VIEW FILTER **************************************************************************
    private List<StudentListResponse> al_searchlist;


    public StudentAdapter(AllStudentActivity allStudentActivity, List<StudentListResponse> studentResponseArrayList) {

      this.context=allStudentActivity;
      this.studentListResponses=studentResponseArrayList;

        // SEARCH VIEW FILTER **************************************************************************
        this.al_searchlist = new ArrayList<StudentListResponse>();
        al_searchlist.addAll(studentListResponses);

    }

    public void setNameList(Context context,final List<StudentListResponse> mArrayList){
        this.context = context;

// CODE FOR SEARCH VIEW FILTER *********************************************************************
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return StudentAdapter.this.al_searchlist.size();
            }

            @Override
            public int getNewListSize() {
                return mArrayList.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return StudentAdapter.this.al_searchlist.get(oldItemPosition).getName() == mArrayList.get(newItemPosition).getName();
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                StudentListResponse newMovie = StudentAdapter.this.al_searchlist.get(oldItemPosition);

                StudentListResponse oldMovie = mArrayList.get(newItemPosition);

                return newMovie.getName() == oldMovie.getName() ;
            }
        });

        this.al_searchlist = mArrayList;
        this.studentListResponses = mArrayList;
        result.dispatchUpdatesTo(this);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final StudentListResponse studentListResponse = studentListResponses.get(position);

        holder.student_name.setText(studentListResponse.getName());
        holder.fee_status.setText(studentListResponse.getFees_status());

        holder.txt_course.setText(studentListResponse.getCourse());
        holder.txt_class.setText(studentListResponse.getSection_class());
        holder.txt_fees.setText(studentListResponse.getFees());
        holder.student_shift.setText(studentListResponse.getShift());
        holder.student_type.setText(studentListResponse.getStudent_type());

        String img_user = studentListResponse.getGender();

        String student_type = studentListResponse.getStudent_type();

        if(student_type.equals("Deeniyat")){

            holder.student_type.setBackgroundResource(R.drawable.button_bg2);


        }
        else if(student_type.equals("Balighaan")){

            holder.student_type.setBackgroundResource(R.drawable.button_bg1);

        }

        if(img_user.equals("male")){

             holder.user_img.setImageResource(R.drawable.man);

        }
        else if(img_user.equalsIgnoreCase("female")){

            holder.user_img.setImageResource(R.drawable.girl);

        }

       holder.txt_details.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Toast.makeText(context,"Assalaam Aleikum "+studentListResponse.getStdId(),Toast.LENGTH_LONG).show();

               Intent i = new Intent(context,ProfileActivity.class);
               i.putExtra("std_id",studentListResponse.getStdId());
               i.putExtra("name",studentListResponse.getName());
               i.putExtra("father_name",studentListResponse.getFatherName());
               i.putExtra("father_num",studentListResponse.getFatherMobile());
               i.putExtra("address",studentListResponse.getAddress());
               i.putExtra("gender",studentListResponse.getGender());
               i.putExtra("course",studentListResponse.getCourse());
               i.putExtra("fees",studentListResponse.getFees());



               i.putExtra("student_type",studentListResponse.getStudent_type());

               i.putExtra("fees_status",studentListResponse.getFees_status());
               i.putExtra("alt_mobile",studentListResponse.getAlt_mobile());
               i.putExtra("class",studentListResponse.getSection_class());
               i.putExtra("shift",studentListResponse.getShift());
               i.putExtra("doj",studentListResponse.getDoj());


              context.startActivity(i);
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
        return studentListResponses.size();
    }
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    studentListResponses = al_searchlist;
                } else {

                    List<StudentListResponse> filteredList = new ArrayList<>();

                    for (StudentListResponse androidVersion : al_searchlist) {

                        if (androidVersion.getName().toLowerCase().contains(charString) || androidVersion.getStdId().toLowerCase().contains(charString)) {

                            filteredList.add(androidVersion);
                        }
                    }

                    studentListResponses = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = studentListResponses;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                studentListResponses = (List<StudentListResponse>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

      TextView  student_name,fee_status,txt_course,txt_fees,txt_class,student_shift,student_type;
      ImageView user_img,txt_details;

        public MyViewHolder(View itemView) {
            super(itemView);

            student_name = (TextView) itemView.findViewById(R.id.student_name);
            user_img = (ImageView) itemView.findViewById(R.id.img_user);
            fee_status = (TextView) itemView.findViewById(R.id.fee_status);
            txt_course = (TextView) itemView.findViewById(R.id.txt_course);
            txt_fees = (TextView) itemView.findViewById(R.id.txt_fees);
            txt_class = (TextView) itemView.findViewById(R.id.txt_class);

            txt_details   = (ImageView) itemView.findViewById(R.id.img_details);

            student_shift   = (TextView) itemView.findViewById(R.id.student_shift);

            student_type   = (TextView) itemView.findViewById(R.id.student_type);



        }
    }
}
