package simplycodighub.masjid_e_ala.View;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import simplycodighub.masjid_e_ala.Adapter.AttendTrackActivityAdapter;
import simplycodighub.masjid_e_ala.Adapter.Shift2Adapter;
import simplycodighub.masjid_e_ala.Interface.IRestService;
import simplycodighub.masjid_e_ala.Model.AttendTrackActivityResponse;
import simplycodighub.masjid_e_ala.Model.AttendanceListResponse2;
import simplycodighub.masjid_e_ala.R;
import simplycodighub.masjid_e_ala.Util.ApiClient;

public class AttendTrackActivity extends AppCompatActivity {

    LinearLayoutManager linearLayoutManager;
    List<AttendTrackActivityResponse> responseList = new ArrayList<>();
    IRestService service;
    RecyclerView attendanceRecyclerlist;
    AttendTrackActivityAdapter attendaceAdapter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend_track);
        loadData();
        getList();
    }


    private void loadData() {
        attendanceRecyclerlist  = (RecyclerView) findViewById(R.id.listview);
        attendaceAdapter = new AttendTrackActivityAdapter(AttendTrackActivity.this,responseList);
        linearLayoutManager = new LinearLayoutManager(this); // Define layout manager current state
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);  // Set layout orientation to horizontal
        attendanceRecyclerlist.setLayoutManager(linearLayoutManager);  // Set linearLayoutManager  to jobrecyclerlist
        attendanceRecyclerlist.setAdapter(attendaceAdapter);  // Added jobrecycler to adapter

    }

    private void getList() {

        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        service = ApiClient.getRetrofit().create(IRestService.class);
        service.getAttendTrackList().enqueue(new Callback<List<AttendTrackActivityResponse>>() {
            @Override
            public void onResponse(Call<List<AttendTrackActivityResponse>> call, Response<List<AttendTrackActivityResponse>> response) {


                progressDialog.hide();
                Log.d("zzz","zzzzzzz"+response.body());



                responseList.addAll(response.body());
                attendaceAdapter.notifyDataSetChanged();



            }

            @Override
            public void onFailure(Call<List<AttendTrackActivityResponse>> call, Throwable t) {

            }
        });
    }
}
