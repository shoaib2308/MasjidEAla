package simplycodighub.masjid_e_ala.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import simplycodighub.masjid_e_ala.Interface.IRestService;
import simplycodighub.masjid_e_ala.Model.CountResponse;
import simplycodighub.masjid_e_ala.R;
import simplycodighub.masjid_e_ala.Util.ApiClient;

public class MainActivity extends AppCompatActivity {
LinearLayout linear_btn,linear_btn2,linear_btn3;
TextView std_count;
IRestService iRestService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linear_btn = (LinearLayout) findViewById(R.id.linear_btn);
        linear_btn2 = (LinearLayout) findViewById(R.id.linear_btn2);

        linear_btn3 = (LinearLayout) findViewById(R.id.linear_bt3);

        std_count = (TextView) findViewById(R.id.std_count);

        linear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AddStudentActivity.class);
                startActivity(i);
            }
        });

        linear_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AllStudentActivity.class);
                startActivity(i);
            }
        });
        linear_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent i = new Intent(MainActivity.this,AttendanceActivity.class);
                Intent i = new Intent(MainActivity.this,AttendanceActivityFilter.class);
                startActivity(i);
            }
        });

        loadData();
    }

    private void loadData() {
        iRestService  = ApiClient.getRetrofit().create(IRestService.class);
        iRestService.getStudentCount().enqueue(new Callback<CountResponse>() {
            @Override
            public void onResponse(Call<CountResponse> call, Response<CountResponse> response) {

             String count = response.body().getStdCount().toString();
             std_count.setText(count);
            }

            @Override
            public void onFailure(Call<CountResponse> call, Throwable t) {

            }
        });

    }
}
