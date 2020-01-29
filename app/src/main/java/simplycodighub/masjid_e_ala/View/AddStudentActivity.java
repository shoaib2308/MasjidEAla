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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import simplycodighub.masjid_e_ala.Interface.IRestService;
import simplycodighub.masjid_e_ala.Model.RegisterResponse;
import simplycodighub.masjid_e_ala.R;
import simplycodighub.masjid_e_ala.Util.ApiClient;

public class AddStudentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{

    EditText et_student_name,et_father_name,et_father_mobile,et_gender,et_address,et_alt_mobile,et_fees;
    Button btn_submit;
    IRestService service;
    ProgressDialog progressDialog;

    RadioGroup rg;
    RadioButton rb;

    RadioGroup rg2;
    RadioButton rb2;

    RadioGroup rg3;
    RadioButton rb3;

    RadioGroup rg4;
    RadioButton rb4;

    RadioGroup rg5;
    RadioButton rb5;

    String radi_val_gender;


    String[] classy = {"Select Class","Class A", "Class B", "Class C", "Class D"};


    String val_course;

    String spin_val_class;

    String student_shift;

    String student_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);


        et_student_name = (EditText) findViewById(R.id.et_student_name);
        et_father_name = (EditText) findViewById(R.id.et_father_name);
        et_father_mobile = (EditText) findViewById(R.id.et_father_mobile);
        et_address = (EditText) findViewById(R.id.et_address);
        et_alt_mobile = (EditText) findViewById(R.id.et_alt_mobile);
        et_fees = (EditText) findViewById(R.id.et_fees);

        rg=(RadioGroup)findViewById(R.id.radioGroup);
        rg2=(RadioGroup)findViewById(R.id.radioGroup2);
        rg3=(RadioGroup)findViewById(R.id.radioGroup3);
        rg4=(RadioGroup)findViewById(R.id.radioGroup4);
        rg5=(RadioGroup)findViewById(R.id.radiocourse4);


        btn_submit = (Button) findViewById(R.id.btn_submit);



        Spinner spin2 = (Spinner) findViewById(R.id.spinner_class);
        spin2.setOnItemSelectedListener(this);
        //spin3.setVisibility(View.VISIBLE);


        //Creating the ArrayAdapter instance having the class list
        ArrayAdapter bb = new ArrayAdapter(this,android.R.layout.simple_spinner_item,classy);
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        spin2.setAdapter(bb);





        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String student_name = et_student_name.getText().toString();
                String father_name = et_father_name.getText().toString();
                String father_mobile = et_father_mobile.getText().toString();
                String address = et_address.getText().toString();
                String alt_mobile = et_alt_mobile.getText().toString();
                String fees = et_fees.getText().toString();


                int selectedId=rg.getCheckedRadioButtonId();
                rb=(RadioButton)findViewById(selectedId);

                if(student_name.equals("")||father_name.equals("")||father_mobile.equals("")||address.equals("")){
                //if(TextUtils.isEmpty(student_name)){

                    Toast.makeText(AddStudentActivity.this,"Please Insert Filled",Toast.LENGTH_LONG).show();


                }
                else{
                // Directly going to server
           PostRegister(student_name,father_name,father_mobile,radi_val_gender,address,val_course,alt_mobile,fees,spin_val_class,student_type,student_shift);
                }


            }
        });



    }

    public void rbclick(View v){

        int radioButtonid = rg.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(radioButtonid);

        Toast.makeText(AddStudentActivity.this,""+rb.getText(),Toast.LENGTH_LONG).show();

        radi_val_gender = (String) rb.getText();
    }
    public void rbclick2(View v){

        int radioButtonid = rg2.getCheckedRadioButtonId();
        rb2 = (RadioButton) findViewById(radioButtonid);

        String radi_val_two = (String) rb2.getText();

        student_type  = (String) rb2.getText();

        if(radi_val_two.equals("Deeniyat")){


            student_type  = radi_val_two;

            Toast.makeText(AddStudentActivity.this,""+student_type,Toast.LENGTH_LONG).show();

            rg3=(RadioGroup)findViewById(R.id.radioGroup3);
            rg3.setVisibility(View.VISIBLE);

            rg4=(RadioGroup)findViewById(R.id.radioGroup4);
            rg4.setVisibility(View.GONE);
        }
        else if(radi_val_two.equals("Balighaan")){

            student_type  = radi_val_two;

            Toast.makeText(AddStudentActivity.this,""+student_type,Toast.LENGTH_LONG).show();

            rg4=(RadioGroup)findViewById(R.id.radioGroup4);
            rg4.setVisibility(View.VISIBLE);



            rg3=(RadioGroup)findViewById(R.id.radioGroup3);
            rg3.setVisibility(View.GONE);
        }

    }

    public void rbclick3(View v){

        int radioButtonid = rg3.getCheckedRadioButtonId();
        rb3 = (RadioButton) findViewById(radioButtonid);
        student_shift = (String) rb3.getText();
         //Toast.makeText(AddStudentActivity.this,""+rb3.getText(),Toast.LENGTH_LONG).show();
    }

    public void rbclick4(View v){

        int radioButtonid = rg4.getCheckedRadioButtonId();
        rb4 = (RadioButton) findViewById(radioButtonid);
        student_shift = (String) rb4.getText();
        //Toast.makeText(AddStudentActivity.this,""+rb3.getText(),Toast.LENGTH_LONG).show();
    }
    public void rbclick5(View v){

        int radioButtonid = rg5.getCheckedRadioButtonId();
        rb5 = (RadioButton) findViewById(radioButtonid);
        val_course = (String) rb5.getText();
        //Toast.makeText(AddStudentActivity.this,""+rb3.getText(),Toast.LENGTH_LONG).show();
    }
    private void PostRegister(String s1,String s2, String s3,String s4,String s5,String s6,String s7,String s8,String s9, String s10,String s11) {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();

      service = ApiClient.getRetrofit().create(IRestService.class);
      service.getRegister(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11).enqueue(new Callback<RegisterResponse>() {
          @Override
          public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

             progressDialog.hide();
              String status = response.body().getStatus();

              Toast.makeText(AddStudentActivity.this,"Successfully Registered",Toast.LENGTH_LONG).show();

              if(status.equals("success")){

                  Intent i = new Intent(AddStudentActivity.this,MainActivity.class);
                  startActivity(i);
                  finish();
              }
              else{
                  Toast.makeText(AddStudentActivity.this,"something went wrong or server issue contact developer",Toast.LENGTH_LONG).show();

              }

          }

          @Override
          public void onFailure(Call<RegisterResponse> call, Throwable t) {

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

       // Toast.makeText(getApplicationContext(),course[position] , Toast.LENGTH_LONG).show();

       spin_val_class = classy[position];


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
