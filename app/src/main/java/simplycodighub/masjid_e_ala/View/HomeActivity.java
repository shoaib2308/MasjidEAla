package simplycodighub.masjid_e_ala.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.tooltip.Tooltip;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import simplycodighub.masjid_e_ala.Interface.IRestService;
import simplycodighub.masjid_e_ala.Model.CountResponse;
import simplycodighub.masjid_e_ala.R;
import simplycodighub.masjid_e_ala.Util.ApiClient;

public class HomeActivity extends AppCompatActivity {

    ShimmerFrameLayout mShimmerViewContainer;
    TextView std_count,add_student,all_student;
    LinearLayout student_attend,student_attend_shift2,student_attend_shift3,
            student_attend_shift4,student_attend_shift5,student_1,student_11,student_12,student_2,student_21,student_22,student_3,student_31,student_32,student_4,student_41,student_42,student_5,student_51,student_52;
    IRestService service;
    String std_count_shift1,std_count_shift2,std_count_shift3,std_count_shift4,std_count_shift5;
    String std_count_shift11,std_count_shift12;
    String std_count_shift21,std_count_shift22;
    String std_count_shift31,std_count_shift32;
    String std_count_shift41,std_count_shift42;
    String std_count_shift51,std_count_shift52;
    LinearLayout attend_tracker_shift1,fees_shift1,student_attend0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        std_count = (TextView) findViewById(R.id.std_count);
        add_student = (TextView) findViewById(R.id.add_student);
        all_student= (TextView) findViewById(R.id.all_student);
        student_attend = (LinearLayout) findViewById(R.id.student_attend);
        student_attend0 = (LinearLayout) findViewById(R.id.student_attend0);
        student_attend_shift2 = (LinearLayout) findViewById(R.id.student_attend_shift2);
        student_attend_shift3= (LinearLayout) findViewById(R.id.student_attend_shift3);
        student_attend_shift4 = (LinearLayout) findViewById(R.id.student_attend_shift4);
        student_attend_shift5= (LinearLayout) findViewById(R.id.student_attend_shift5);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);

        attend_tracker_shift1 = (LinearLayout) findViewById(R.id.attend_tracker_shift1);
        fees_shift1 = (LinearLayout) findViewById(R.id.fees_shift1);
        student_1 = (LinearLayout) findViewById(R.id.student_1);
        student_11 = (LinearLayout) findViewById(R.id.student_11);
        student_12 = (LinearLayout) findViewById(R.id.student_12);



          student_2 = (LinearLayout) findViewById(R.id.student_2);
          student_21 = (LinearLayout) findViewById(R.id.student_21);
          student_22 = (LinearLayout) findViewById(R.id.student_22);



          student_3 = (LinearLayout) findViewById(R.id.student_3);
          student_31 = (LinearLayout) findViewById(R.id.student_31);
          student_32 = (LinearLayout) findViewById(R.id.student_32);


         student_4 = (LinearLayout) findViewById(R.id.student_4);
         student_41 = (LinearLayout) findViewById(R.id.student_41);
         student_42 = (LinearLayout) findViewById(R.id.student_42);

         student_5 = (LinearLayout) findViewById(R.id.student_5);
         student_51 = (LinearLayout) findViewById(R.id.student_51);
         student_52 = (LinearLayout) findViewById(R.id.student_52);





// SHIFT 1  All Detail
        student_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student_1  = (LinearLayout) findViewById(R.id.student_1);
                Tooltip tooltip = new Tooltip.Builder(student_1,R.style.Tooltip2)
                        .setText("Shift-1 All Total :- "+std_count_shift1)
                        //.setText(R.string.toltip)
                        .setDismissOnClick(true)
                        .setGravity(Gravity.BOTTOM)
                        .setCornerRadius(10f)
                        .show();
            }
        });
        student_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student_11  = (LinearLayout) findViewById(R.id.student_11);
                Tooltip tooltip = new Tooltip.Builder(student_11,R.style.Tooltip2)
                        .setText("Shift-1 Total Male:- "+std_count_shift11)
                        //.setText(R.string.toltip)
                        .setDismissOnClick(true)
                        .setGravity(Gravity.BOTTOM)
                        .setCornerRadius(10f)
                        .show();
            }
        });
        student_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student_12  = (LinearLayout) findViewById(R.id.student_12);
                Tooltip tooltip = new Tooltip.Builder(student_12,R.style.Tooltip2)
                        .setText("Shift-1 Total Female :- "+std_count_shift12)
                        //.setText(R.string.toltip)
                        .setDismissOnClick(true)
                        .setGravity(Gravity.BOTTOM)
                        .setCornerRadius(10f)
                        .show();
            }
        });

        attend_tracker_shift1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attend_tracker_shift1  = (LinearLayout) findViewById(R.id.attend_tracker_shift1);
              Intent i = new Intent(HomeActivity.this,AttendTrackActivity.class);
               startActivity(i);
            }
        });
        fees_shift1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fees_shift1  = (LinearLayout) findViewById(R.id.fees_shift1);
                Tooltip tooltip = new Tooltip.Builder(fees_shift1,R.style.Tooltip2)
                        .setText("Shift-1  Fees Paid 16/28 ")
                        //.setText(R.string.toltip)
                        .setDismissOnClick(true)
                        .setGravity(Gravity.BOTTOM)
                        .setCornerRadius(10f)
                        .show();




            }
        });



// SHIFT 2  All Detail
        student_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student_2  = (LinearLayout) findViewById(R.id.student_2);
                Tooltip tooltip = new Tooltip.Builder(student_2,R.style.Tooltip2)
                        .setText("Shift-2 All Total :- "+std_count_shift2)
                        //.setText(R.string.toltip)
                        .setDismissOnClick(true)
                        .setGravity(Gravity.BOTTOM)
                        .setCornerRadius(10f)
                        .show();
            }
        });
        student_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student_21  = (LinearLayout) findViewById(R.id.student_21);
                Tooltip tooltip = new Tooltip.Builder(student_21,R.style.Tooltip2)
                        .setText("Shift-2 Male Total :- "+std_count_shift21)
                        //.setText(R.string.toltip)
                        .setDismissOnClick(true)
                        .setGravity(Gravity.BOTTOM)
                        .setCornerRadius(10f)
                        .show();
            }
        });
        student_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student_22  = (LinearLayout) findViewById(R.id.student_22);
                Tooltip tooltip = new Tooltip.Builder(student_22,R.style.Tooltip2)
                        .setText("Shift-2 Female Total :- "+std_count_shift22)
                        //.setText(R.string.toltip)
                        .setDismissOnClick(true)
                        .setGravity(Gravity.BOTTOM)
                        .setCornerRadius(10f)
                        .show();
            }
        });





// SHIFT 3  All Detail
        student_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student_3  = (LinearLayout) findViewById(R.id.student_3);
                Tooltip tooltip = new Tooltip.Builder(student_3,R.style.Tooltip2)
                        .setText("Shift-3 All Total :- "+std_count_shift3)
                        //.setText(R.string.toltip)
                        .setDismissOnClick(true)
                        .setGravity(Gravity.BOTTOM)
                        .setCornerRadius(10f)
                        .show();
            }
        });
        student_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student_31  = (LinearLayout) findViewById(R.id.student_31);
                Tooltip tooltip = new Tooltip.Builder(student_31,R.style.Tooltip2)
                        .setText("Shift-3 Male Total :- "+std_count_shift31)
                        //.setText(R.string.toltip)
                        .setDismissOnClick(true)
                        .setGravity(Gravity.BOTTOM)
                        .setCornerRadius(10f)
                        .show();
            }
        });
        student_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student_32  = (LinearLayout) findViewById(R.id.student_32);
                Tooltip tooltip = new Tooltip.Builder(student_32,R.style.Tooltip2)
                        .setText("Shift-3 Female Total :- "+std_count_shift32)
                        //.setText(R.string.toltip)
                        .setDismissOnClick(true)
                        .setGravity(Gravity.BOTTOM)
                        .setCornerRadius(10f)
                        .show();
            }
        });
 // SHIFT 4  All Detail
        student_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student_4  = (LinearLayout) findViewById(R.id.student_4);
                Tooltip tooltip = new Tooltip.Builder(student_4,R.style.Tooltip2)
                        .setText("Shift-4 All Total :- "+std_count_shift4)
                        //.setText(R.string.toltip)
                        .setDismissOnClick(true)
                        .setGravity(Gravity.BOTTOM)
                        .setCornerRadius(10f)
                        .show();
            }
        });
        student_41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student_41  = (LinearLayout) findViewById(R.id.student_41);
                Tooltip tooltip = new Tooltip.Builder(student_41,R.style.Tooltip2)
                        .setText("Shift-4 Total :- "+std_count_shift41)
                        //.setText(R.string.toltip)
                        .setDismissOnClick(true)
                        .setGravity(Gravity.BOTTOM)
                        .setCornerRadius(10f)
                        .show();
            }
        });
        student_42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student_42  = (LinearLayout) findViewById(R.id.student_42);
                Tooltip tooltip = new Tooltip.Builder(student_42,R.style.Tooltip2)
                        .setText("Shift-4 Total :- "+std_count_shift42)
                        //.setText(R.string.toltip)
                        .setDismissOnClick(true)
                        .setGravity(Gravity.BOTTOM)
                        .setCornerRadius(10f)
                        .show();
            }
        });
// SHIFT 5  All Detail
        student_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student_5  = (LinearLayout) findViewById(R.id.student_5);
                Tooltip tooltip = new Tooltip.Builder(student_5,R.style.Tooltip2)
                        .setText("Shift-5 All Total :- "+std_count_shift5)
                        //.setText(R.string.toltip)
                        .setDismissOnClick(true)
                        .setGravity(Gravity.BOTTOM)
                        .setCornerRadius(10f)
                        .show();
            }
        });
        student_51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student_51  = (LinearLayout) findViewById(R.id.student_51);
                Tooltip tooltip = new Tooltip.Builder(student_51,R.style.Tooltip2)
                        .setText("Shift-5 Total :- "+std_count_shift51)
                        //.setText(R.string.toltip)
                        .setDismissOnClick(true)
                        .setGravity(Gravity.BOTTOM)
                        .setCornerRadius(10f)
                        .show();
            }
        });
        student_52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student_52  = (LinearLayout) findViewById(R.id.student_52);
                Tooltip tooltip = new Tooltip.Builder(student_52,R.style.Tooltip2)
                        .setText("Shift-5 Total :- "+std_count_shift52)
                        //.setText(R.string.toltip)
                        .setDismissOnClick(true)
                        .setGravity(Gravity.BOTTOM)
                        .setCornerRadius(10f)
                        .show();
            }
        });


        student_attend0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomeActivity.this,ShiftZeroActivity.class);
                startActivity(i);
            }
        });

        add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomeActivity.this,AddStudentActivity.class);
                startActivity(i);
            }
        });
        student_attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomeActivity.this,ShiftOneActivity.class);
                startActivity(i);
            }
        });
        student_attend_shift2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomeActivity.this,ShiftTwoActivity.class);
                startActivity(i);
            }
        });
        student_attend_shift3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomeActivity.this,ShiftThreeActivity.class);
                startActivity(i);
            }
        });

        student_attend_shift4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomeActivity.this,ShiftFourActivity.class);
                startActivity(i);
            }
        });
        student_attend_shift5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomeActivity.this,ShiftFiveActivity.class);
                startActivity(i);
            }
        });
       all_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent i = new Intent(HomeActivity.this,AttendanceActivity.class);
               Intent i = new Intent(HomeActivity.this,AllStudentActivity.class);
                startActivity(i);
            }
        });
        // data load
        loadData();

    }

    private void loadData() {

        service = ApiClient.getRetrofit().create(IRestService.class);
        service.getStudentCount().enqueue(new Callback<CountResponse>() {
            @Override
            public void onResponse(Call<CountResponse> call, Response<CountResponse> response) {

               // mShimmerViewContainer.stopShimmerAnimation();
                String count = response.body().getStdCount().toString();
                std_count.setText(count);

                std_count_shift1 = response.body().getStdCountShft1().toString();
                std_count_shift11 = response.body().getStdCountShft1Male().toString();
                std_count_shift12 = response.body().getStdCountShft1Female().toString();


                std_count_shift2 = response.body().getStdCountShft2().toString();
                std_count_shift21 = response.body().getStdCountShft2Male().toString();
                std_count_shift22 = response.body().getStdCountShft2Female().toString();


                std_count_shift3 = response.body().getStdCountShft3().toString();
                std_count_shift31 = response.body().getStdCountShft3Male().toString();
                std_count_shift32 = response.body().getStdCountShft3Female().toString();


                std_count_shift4 = response.body().getStdCountShft4().toString();
                std_count_shift41 = response.body().getStdCountShft4Male().toString();
                std_count_shift42 = response.body().getStdCountShft4Female().toString();



                std_count_shift5 = response.body().getStdCountShft5().toString();
                std_count_shift51 = response.body().getStdCountShft5Male().toString();
                std_count_shift52 = response.body().getStdCountShft5Female().toString();
            }

            @Override
            public void onFailure(Call<CountResponse> call, Throwable t) {

            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }
}
