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
import simplycodighub.masjid_e_ala.Adapter.MyAdapter3;
import simplycodighub.masjid_e_ala.Interface.IRestService;
import simplycodighub.masjid_e_ala.Model.AttendanceListResponse3;
import simplycodighub.masjid_e_ala.R;
import simplycodighub.masjid_e_ala.Util.ApiClient;

import static simplycodighub.masjid_e_ala.View.AddStudentActivity.createProgressDialog;


public class Tab3 extends Fragment implements MyAdapter3.CallBack,SwipeRefreshLayout.OnRefreshListener{
    IRestService service;
    LinearLayoutManager linearLayoutManager3;
    MyAdapter3 adapter;
    RecyclerView lv;
    ArrayList<AttendanceListResponse3> xyz =  new ArrayList<>();
    final  Tab3 context = this;

    ProgressDialog progressDialog;

    /********    All initialization done here   ***********/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_tab3, container, false);

        lv= (RecyclerView) v.findViewById(R.id.listview);

        loadAdapter3();

        getList3();


        return v;
    }

    private void getList3() {

        //progressDialog =createProgressDialog(getActivity());
        //progressDialog.show();

        service = ApiClient.getRetrofit().create(IRestService.class);
        service.getAttendanceList3().enqueue(new Callback<List<AttendanceListResponse3>>() {
            @Override
            public void onResponse(Call<List<AttendanceListResponse3>> call, Response<List<AttendanceListResponse3>> response) {

                //progressDialog.hide();

                xyz.addAll(response.body());
                Log.d("jjjjjj",""+xyz);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<AttendanceListResponse3>> call, Throwable t) {

            }
        });

    }


    private void loadAdapter3() {

        adapter = new MyAdapter3(xyz,Tab3.this);
        linearLayoutManager3 = new LinearLayoutManager(getActivity());
        linearLayoutManager3.setOrientation(LinearLayoutManager.VERTICAL);
        lv.setLayoutManager(linearLayoutManager3);
        lv.setAdapter(adapter);

    }
    @Override
    public void onRefresh() {
        xyz.clear();
        loadAdapter3();

        getList3();
    }

    @Override
    public void imageButtonOnClick() {
        onRefresh();
    }

}
