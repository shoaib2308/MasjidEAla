package simplycodighub.masjid_e_ala.View;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import simplycodighub.masjid_e_ala.Adapter.Shift4Adapter;
import simplycodighub.masjid_e_ala.Adapter.Shift5Adapter;
import simplycodighub.masjid_e_ala.Interface.IRestService;
import simplycodighub.masjid_e_ala.Model.AttendanceListResponse4;
import simplycodighub.masjid_e_ala.Model.AttendanceListResponse5;
import simplycodighub.masjid_e_ala.R;
import simplycodighub.masjid_e_ala.Util.ApiClient;

public class ShiftFiveActivity extends AppCompatActivity {
    LinearLayoutManager linearLayoutManager;
    List<AttendanceListResponse5> attendanceResponseArrayList = new ArrayList<>();
    IRestService service;
    RecyclerView attendanceRecyclerlist;
    Shift5Adapter attendaceAdapter;
    ProgressDialog progressDialog;
    ShimmerFrameLayout mShimmerViewContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_five);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        loadAdapter();
        getList();
    }

    public void loadAdapter() {

        attendanceRecyclerlist  = (RecyclerView) findViewById(R.id.listview);
        attendaceAdapter = new Shift5Adapter(ShiftFiveActivity.this,attendanceResponseArrayList);
        linearLayoutManager = new LinearLayoutManager(this); // Define layout manager current state
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);  // Set layout orientation to horizontal
        attendanceRecyclerlist.setLayoutManager(linearLayoutManager);  // Set linearLayoutManager  to jobrecyclerlist
        attendanceRecyclerlist.setAdapter(attendaceAdapter);  // Added jobrecycler to adapter
    }
    private void getList() {
        progressDialog = new ProgressDialog(this);
       // progressDialog.show();
        service = ApiClient.getRetrofit().create(IRestService.class);
        service.getAttendanceList5().enqueue(new Callback<List<AttendanceListResponse5>>() {
            @Override
            public void onResponse(Call<List<AttendanceListResponse5>> call, Response<List<AttendanceListResponse5>> response) {


               // progressDialog.hide();
                Log.d("ggggg","kkkkkkk"+response.body());



                attendanceResponseArrayList.addAll(response.body());
                attendaceAdapter.notifyDataSetChanged();

                // Search view filter**************************************************************
                // calling  setNameList method   for filter purpose
                attendaceAdapter.setNameList(getApplicationContext(),attendanceResponseArrayList);

                // stop animating Shimmer and hide the layout
                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<AttendanceListResponse5>> call, Throwable t) {

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
    /*code start for Search view filter ************************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                attendaceAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    public static ProgressDialog createProgressDialog(Context mContext) {
        ProgressDialog dialog = new ProgressDialog(mContext);
        try {
            dialog.show();
        } catch (WindowManager.BadTokenException e) {

        }
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.progress_dialogbar);
        dialog.setMessage("loading..");
        return dialog;
    }
}

