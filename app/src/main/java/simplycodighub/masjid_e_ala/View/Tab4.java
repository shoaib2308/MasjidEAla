package simplycodighub.masjid_e_ala.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import simplycodighub.masjid_e_ala.Adapter.MyAdapter1;
import simplycodighub.masjid_e_ala.Adapter.MyAdapter4;
import simplycodighub.masjid_e_ala.Interface.IRestService;
import simplycodighub.masjid_e_ala.Model.AttendanceListResponse4;
import simplycodighub.masjid_e_ala.R;
import simplycodighub.masjid_e_ala.Util.ApiClient;

import static simplycodighub.masjid_e_ala.View.AddStudentActivity.createProgressDialog;

public class Tab4 extends Fragment implements MyAdapter1.CallBack,SwipeRefreshLayout.OnRefreshListener{
    IRestService service;
    LinearLayoutManager linearLayoutManager;
    MyAdapter4 adapter;
    RecyclerView lv;
    ArrayList<AttendanceListResponse4> xyz =  new ArrayList<>();
    final  Tab4 context = this;

    ProgressDialog progressDialog;

    /********    All initialization done here   ***********/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_tab4, container, false);

        lv= (RecyclerView) v.findViewById(R.id.listview);

        loadAdapter();

        getList();


        return v;
    }

    private void getList() {

        //progressDialog =createProgressDialog(getActivity());
        //progressDialog.show();

        service = ApiClient.getRetrofit().create(IRestService.class);
        service.getAttendanceList4().enqueue(new Callback<List<AttendanceListResponse4>>() {
            @Override
            public void onResponse(Call<List<AttendanceListResponse4>> call, Response<List<AttendanceListResponse4>> response) {

                //progressDialog.hide();

                xyz.addAll(response.body());
                Log.d("jjjjjj",""+xyz);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<AttendanceListResponse4>> call, Throwable t) {

            }
        });

    }



    private void loadAdapter() {

        adapter = new MyAdapter4(xyz,Tab4.this);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lv.setLayoutManager(linearLayoutManager);
        lv.setAdapter(adapter);

    }
    @Override
    public void onRefresh() {
        xyz.clear();
        loadAdapter();

        getList();
    }

    @Override
    public void imageButtonOnClick() {
        onRefresh();
    }
}
