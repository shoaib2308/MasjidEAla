package simplycodighub.masjid_e_ala.View;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import simplycodighub.masjid_e_ala.Interface.IRestService;
import simplycodighub.masjid_e_ala.Model.PaymentResponse;
import simplycodighub.masjid_e_ala.Model.RegisterResponse;
import simplycodighub.masjid_e_ala.R;
import simplycodighub.masjid_e_ala.Util.ApiClient;

public class PaymentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] month = {"Select Month","Jan", "Feb", "Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

    String spinner_val_month;

    TextView et_stdid,et_name,et_year,et_amount;

    IRestService service;

    String std_id, name,year,amount;

    Button btn_submit;

    ProgressDialog progressDialog;

    private HashMap<String, String> hash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        //i.putExtra("stdid",i.getStringExtra("std_id"));

         et_stdid = (TextView) findViewById(R.id.et_stdid);
         et_name = (TextView) findViewById(R.id.et_name);

        et_year = (TextView) findViewById(R.id.et_year);
        et_amount = (TextView) findViewById(R.id.et_amount);
        btn_submit = (Button)  findViewById(R.id.btn_submit);

        Spinner spin = (Spinner) findViewById(R.id.spinner_month);
        spin.setOnItemSelectedListener(this);

       //Creating the ArrayAdapter instance having the month list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,month);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);





        Intent i = getIntent();

         std_id = i.getStringExtra("stdid");
         name = i.getStringExtra("name");

        et_stdid.setText(std_id);
        et_name.setText(name);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                year =  et_year.getText().toString();

                amount = et_amount.getText().toString();

                //Toast.makeText(PaymentActivity.this,""+year+amount+spinner_val_month,Toast.LENGTH_LONG).show();

                hash=new HashMap<String, String>();

                hash.put("std_id",std_id);
                hash.put("year",year);
                hash.put("amount",amount);
                hash.put("month",spinner_val_month);
                PaymentUpdate();


            }
        });


    }

    private void PaymentUpdate() {

        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        service = ApiClient.getRetrofit().create(IRestService.class);
        service.getPayment(hash).enqueue(new Callback<PaymentResponse>() {
            @Override
            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {

                progressDialog.hide();

                String status = response.body().getStatus();

                if(status.equals("success")){

                    Toast.makeText(PaymentActivity.this,"Successfully Updated",Toast.LENGTH_LONG).show();

                    Intent i = new Intent(PaymentActivity.this,AllStudentActivity.class);
                    startActivity(i);
                    finish();
                }
                else{

                    Toast.makeText(PaymentActivity.this,"Not Updated",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<PaymentResponse> call, Throwable t) {

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        spinner_val_month = month[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
