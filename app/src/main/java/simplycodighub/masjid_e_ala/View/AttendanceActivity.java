package simplycodighub.masjid_e_ala.View;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import simplycodighub.masjid_e_ala.Adapter.AttendaceAdapter;
import simplycodighub.masjid_e_ala.Interface.IRestService;
import simplycodighub.masjid_e_ala.Model.AttendanceListResponse1;
import simplycodighub.masjid_e_ala.R;
import simplycodighub.masjid_e_ala.Util.ApiClient;

public class AttendanceActivity extends AppCompatActivity {
    LinearLayoutManager linearLayoutManager;
    List<AttendanceListResponse1> attendanceResponseArrayList = new ArrayList<>();
    IRestService service;
    RecyclerView attendanceRecyclerlist;
    AttendaceAdapter attendaceAdapter;
    ProgressDialog progressDialog;
    TextView curr_day_p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        curr_day_p = (TextView) findViewById(R.id.curr_day_p);


        loadAdapter();
        getList();
        getCurrentDate();
    }

    private void getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = "Today Date : " + mdformat.format(calendar.getTime());
        display(strDate);
    }

    private void display(String num) {
        TextView textView = (TextView) findViewById(R.id.current_date_view);
        textView.setText(num);
    }
    public void loadAdapter() {

        attendanceRecyclerlist  = (RecyclerView) findViewById(R.id.attendancelistview);
        attendaceAdapter = new AttendaceAdapter(AttendanceActivity.this,attendanceResponseArrayList);
        linearLayoutManager = new LinearLayoutManager(this); // Define layout manager current state
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);  // Set layout orientation to horizontal
        attendanceRecyclerlist.setLayoutManager(linearLayoutManager);  // Set linearLayoutManager  to jobrecyclerlist
        attendanceRecyclerlist.setAdapter(attendaceAdapter);  // Added jobrecycler to adapter
    }

    private void getList() {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        service = ApiClient.getRetrofit().create(IRestService.class);
        service.getAttendanceList1().enqueue(new Callback<List<AttendanceListResponse1>>() {
            @Override
            public void onResponse(Call<List<AttendanceListResponse1>> call, Response<List<AttendanceListResponse1>> response) {


                progressDialog.hide();
                Log.d("ggggg","kkkkkkk"+response.body());



                attendanceResponseArrayList.addAll(response.body());
                attendaceAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<AttendanceListResponse1>> call, Throwable t) {

            }
        });

    }






}
