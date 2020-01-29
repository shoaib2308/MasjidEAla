package simplycodighub.masjid_e_ala.View;

import android.app.ProgressDialog;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import simplycodighub.masjid_e_ala.Interface.IRestService;
import simplycodighub.masjid_e_ala.Model.StudentListResponse;
import simplycodighub.masjid_e_ala.R;
import simplycodighub.masjid_e_ala.Adapter.StudentAdapter;
import simplycodighub.masjid_e_ala.Util.ApiClient;

public class AllStudentActivity extends AppCompatActivity {


    LinearLayoutManager linearLayoutManager;
    List<StudentListResponse> studentResponseArrayList = new ArrayList<>();
    IRestService service;
    RecyclerView studentRecyclerlist;
    StudentAdapter studentAdapter;
    ShimmerFrameLayout mShimmerViewContainer;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_student);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        loadAdapter();
        getList();

    }





    public void loadAdapter() {

        studentRecyclerlist  = (RecyclerView) findViewById(R.id.studentlistview);
        studentAdapter = new StudentAdapter(AllStudentActivity.this,studentResponseArrayList);
        linearLayoutManager = new LinearLayoutManager(this); // Define layout manager current state
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);  // Set layout orientation to horizontal
        studentRecyclerlist.setLayoutManager(linearLayoutManager);  // Set linearLayoutManager  to jobrecyclerlist
        studentRecyclerlist.setAdapter(studentAdapter);  // Added jobrecycler to adapter

    }

    private void getList() {

        progressDialog = new ProgressDialog(this);
        //progressDialog.show();
        service = ApiClient.getRetrofit().create(IRestService.class);
        service.getStudentList().enqueue(new Callback<List<StudentListResponse>>() {
            @Override
            public void onResponse(Call<List<StudentListResponse>> call, Response<List<StudentListResponse>> response) {

              //  progressDialog.hide();

                Log.d("ggggg","kkkkkkk"+response.body());
                studentResponseArrayList.addAll(response.body());
                studentAdapter.notifyDataSetChanged();

                // Search view filter**************************************************************
                // calling  setNameList method   for filter purpose
                studentAdapter.setNameList(getApplicationContext(),studentResponseArrayList);



                // stop animating Shimmer and hide the layout
                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<StudentListResponse>> call, Throwable t) {

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

                studentAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}

