package simplycodighub.masjid_e_ala.View;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import simplycodighub.masjid_e_ala.Interface.IRestService;
import simplycodighub.masjid_e_ala.Model.EditProfileResponse;
import simplycodighub.masjid_e_ala.Model.PaymentResponse;
import simplycodighub.masjid_e_ala.R;
import simplycodighub.masjid_e_ala.Util.ApiClient;

public class EditProfileActivity extends AppCompatActivity {

    final Context context = this;

    EditText et_std_id,et_name,et_father_name,et_father_mobile,radioGroup,et_address,et_fees,et_alt_mobile;

    String std_id,name,father_name,father_num,address,gender,course,fees,alt_mobile,student_type,class_type;

    Button btn_submit;

    ProgressDialog progressDialog;

    private HashMap<String, String> hash;
    String shift;

    RadioGroup rg;
    RadioButton rb;

    RadioGroup rg2;
    RadioButton rb2;

    RadioGroup rg3;
    RadioButton rb3;

    RadioGroup rg4;
    RadioButton rb4;

    RadioGroup rg6;
    RadioButton rb6;

    IRestService service;
    String radi_val_gender,val_course,val_class,val_shift;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Context context = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        et_std_id = (EditText) findViewById(R.id.et_std_id);
        et_name = (EditText) findViewById(R.id.et_name);
        et_father_name =  (EditText) findViewById(R.id.et_father_name);
        et_father_mobile =  (EditText) findViewById(R.id.et_father_mobile);
        et_address =  (EditText) findViewById(R.id.et_address);
        et_fees =  (EditText) findViewById(R.id.et_fees);
        et_alt_mobile =  (EditText) findViewById(R.id.et_alt_mobile);
        rg=(RadioGroup)findViewById(R.id.radioGroup);
        rb=(RadioButton)findViewById(R.id.radioButton);
        rg2=(RadioGroup)findViewById(R.id.radiocourse4);
        rg6=(RadioGroup)findViewById(R.id.radiocourse6);
        rg3=(RadioGroup)findViewById(R.id.radioGroup3);
        rg4=(RadioGroup)findViewById(R.id.radioGroup4);

        btn_submit = (Button)  findViewById(R.id.btn_update_profile);

        /***********************************  Get Intent Data  **************************************/

        Intent i = getIntent();

        std_id = i.getStringExtra("stdid");
        name = i.getStringExtra("name");
        father_name = i.getStringExtra("father");
        father_num = i.getStringExtra("father_num");
        address = i.getStringExtra("address");
        gender = i.getStringExtra("gender");
        course = i.getStringExtra("course");
        fees = i.getStringExtra("fees");
        alt_mobile = i.getStringExtra("alt_mobile");
        student_type = i.getStringExtra("student_type");
        class_type  = i.getStringExtra("class");
        shift  = i.getStringExtra("shift");





        /*********************************** Assign to edit text or textview  **********************/
        et_std_id.setText(std_id);
        et_name.setText(name);
        et_father_name.setText(father_name);
        et_father_mobile.setText(father_num);
        et_alt_mobile.setText(alt_mobile);
        et_address.setText(address);
        et_fees.setText(fees);


        // GENDER
    /***********************************************************************************************/
        if (gender.equalsIgnoreCase("Male"))
        {
            // no radio buttons are checked
            RadioButton rb1 = (RadioButton) findViewById(R.id.radioButton);
            rb1.setChecked(true);

            radi_val_gender = "Male";
        }
        else if(gender.equalsIgnoreCase("Female"))
        {
            // one of the radio buttons is checked
            RadioButton rb2 = (RadioButton) findViewById(R.id.radioButton2);
            rb2.setChecked(true);
            radi_val_gender = "Female";
        }

        // COURSE
     /********************************************************************************************/

        if (course.equalsIgnoreCase("Noorani qaidah"))
        {
            val_course="Noorani qaidah";
            RadioButton rb1 = (RadioButton) findViewById(R.id.radioButton51);
            rb1.setChecked(true);
        }
        else if(course.equalsIgnoreCase("Quran"))
        {
            val_course="Quran";
            RadioButton rb2 = (RadioButton) findViewById(R.id.radioButton52);
            rb2.setChecked(true);
        }
        else if(course.equalsIgnoreCase("Urdu"))
        {
            val_course="Urdu";
            RadioButton rb3 = (RadioButton) findViewById(R.id.radioButton53);
            rb3.setChecked(true);
        }

        // DEENIYAT
      /********************************************************************************************/
        /*Deeniyat Selection Code*/

        if(student_type.equalsIgnoreCase("Deeniyat")){
            RadioButton rb1 = (RadioButton) findViewById(R.id.radioButton21);
            rb1.setChecked(true);

            RadioGroup rg3 = (RadioGroup) findViewById(R.id.radioGroup3);
            rg3.setVisibility(View.VISIBLE);

            if(shift.equalsIgnoreCase("Shift-1 (03:45PM - 05:00PM)")){
                RadioButton rb = (RadioButton) findViewById(R.id.radioButton31);
                rb.setChecked(true);

                val_shift = "Shift-1 (03:45PM - 05:00PM)";

            }
            else if(shift.equalsIgnoreCase("Shift-2 (05:00PM - 06:15PM)")){
                RadioButton rb = (RadioButton) findViewById(R.id.radioButton32);
                rb.setChecked(true);
                val_shift = "Shift-2 (05:00PM - 06:15PM)";

            }
            else if(shift.equalsIgnoreCase("Shift-3 (06:15PM - 07:30PM)")){
                RadioButton rb = (RadioButton) findViewById(R.id.radioButton33);
                rb.setChecked(true);
                val_shift = "Shift-3 (06:15PM - 07:30PM)";

            }

        }

        // BALIGHAAN
      /*********************************************************************************************/
         /* Baligah of code*/
        else if(student_type.equalsIgnoreCase("Balighaan")){
            RadioButton rb1 = (RadioButton) findViewById(R.id.radioButton22);
            rb1.setChecked(true);

            RadioGroup rg4 = (RadioGroup) findViewById(R.id.radioGroup4);
            rg4.setVisibility(View.VISIBLE);

            if(shift.equalsIgnoreCase("After Fajar")){
                RadioButton rb = (RadioButton) findViewById(R.id.radioButton41);
                rb.setChecked(true);
                val_shift = "After Fajar";
            }
            else if(shift.equalsIgnoreCase("After Isha")){
                RadioButton rb = (RadioButton) findViewById(R.id.radioButton42);
                rb.setChecked(true);
                val_shift = "After Isha";

            }



        }

        // CLASS
        /*********************************************************************************************/


        if(class_type.equalsIgnoreCase("Class A")){
            RadioButton rb61 = (RadioButton) findViewById(R.id.radioButton61);
            rb61.setChecked(true);

            val_class ="Class A";

        }
        else if(class_type.equalsIgnoreCase("Class B")){

            RadioButton rb62 = (RadioButton) findViewById(R.id.radioButton62);
            rb62.setChecked(true);
            val_class ="Class B";

        }
       else if(class_type.equalsIgnoreCase("Class C")){
            RadioButton rb63 = (RadioButton) findViewById(R.id.radioButton63);
            rb63.setChecked(true);
            val_class ="Class C";

        }
       else if(class_type.equalsIgnoreCase("Class D")){

            RadioButton rb64 = (RadioButton) findViewById(R.id.radioButton64);
            rb64.setChecked(true);
            val_class ="Class D";

        }
        /*********************************************************************************************/


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                std_id =  et_std_id.getText().toString();
                name  = et_name.getText().toString();
                father_name  = et_father_name.getText().toString();
                father_num  = et_father_mobile.getText().toString();

                alt_mobile  = et_alt_mobile.getText().toString();
                fees  = et_fees.getText().toString();
                address  = et_address.getText().toString();



                hash=new HashMap<String, String>();

                hash.put("std_id",std_id);
                hash.put("name",name);
                hash.put("father_name",father_name);
                hash.put("fees",fees);
                hash.put("address",address);
                hash.put("father_number",father_num);
                hash.put("alt_number",alt_mobile);
                hash.put("gender",radi_val_gender);
                hash.put("course",val_course);
                hash.put("class",val_class);
                hash.put("shift",val_shift);

               // Toast.makeText(EditProfileActivity.this,""+hash,Toast.LENGTH_LONG).show();
                //Toast.makeText(EditProfileActivity.this,"Successfully Updated"+hash,Toast.LENGTH_LONG).show();

                ProfileUpdate();
            }
        });




    }


    private void ProfileUpdate() {
               progressDialog = new ProgressDialog(this);
        progressDialog.show();


        service = ApiClient.getRetrofit().create(IRestService.class);
        service.getEditProfile(hash).enqueue(new Callback<EditProfileResponse>()
        {
            @Override
            public void onResponse(Call<EditProfileResponse> call, Response<EditProfileResponse> response) {



                progressDialog.hide();

                String status = response.body().getStatus();

                Log.d("ggggg","kkkkkkk"+response.body());

                if(status.equals("success")){

                    Toast.makeText(EditProfileActivity.this,"Successfully Updated",Toast.LENGTH_LONG).show();

                    Intent i = new Intent(EditProfileActivity.this,AllStudentActivity.class);
                    startActivity(i);
                    finish();
                }
                else{

                    Toast.makeText(EditProfileActivity.this,"Not Updated",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<EditProfileResponse> call, Throwable t) {

            }
        });

    }




    /**************************************************** All RB CLICKS  *********************************************/
    public void rbclick(View v){

        int radioButtonid = rg.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(radioButtonid);

        Toast.makeText(EditProfileActivity.this,""+rb.getText(),Toast.LENGTH_LONG).show();

        radi_val_gender = (String) rb.getText();
    }
    public void rbclick5(View v){
        int radioButtonid = rg2.getCheckedRadioButtonId();
        rb2 = (RadioButton) findViewById(radioButtonid);
        val_course = (String) rb2.getText();
        Toast.makeText(EditProfileActivity.this,""+rb2.getText(),Toast.LENGTH_LONG).show();
    }
    public void rbclick6(View v){
        int radioButtonid = rg6.getCheckedRadioButtonId();
        rb6 = (RadioButton) findViewById(radioButtonid);
        val_class = (String) rb6.getText();
        //Toast.makeText(EditProfileActivity.this,""+rb2.getText(),Toast.LENGTH_LONG).show();
    }
    public void rbclick3(View v){
        int radioButtonid = rg3.getCheckedRadioButtonId();
        rb3 = (RadioButton) findViewById(radioButtonid);
        val_shift = (String) rb3.getText();
        //Toast.makeText(EditProfileActivity.this,""+rb2.getText(),Toast.LENGTH_LONG).show();
    }
    public void rbclick4(View v){
        int radioButtonid = rg4.getCheckedRadioButtonId();
        rb4 = (RadioButton) findViewById(radioButtonid);
        val_shift = (String) rb4.getText();
        //Toast.makeText(EditProfileActivity.this,""+rb2.getText(),Toast.LENGTH_LONG).show();
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
