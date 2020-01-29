package simplycodighub.masjid_e_ala.View;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import simplycodighub.masjid_e_ala.R;

public class ProfileActivity extends AppCompatActivity {
    final Context context = this;
    TextView edit_profile,tv_student_id,tv_name,tv_father,tv_father_num,tv_address,tv_fees,tv_course,tv_gender,tv_fees_status,tv_alt_mobile,tv_shift,tv_class,tv_doj,fees_update;
    ImageView profile_image;
    String gender;
    String father_num;
    String fees;
    String course;
    String address;
    String alt_mobile;
    String student_type;
    String classy;
    String shift;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Context context = this;
         Button button;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_student_id = (TextView) findViewById(R.id.tv_student_id);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_father = (TextView) findViewById(R.id.tv_father);
        tv_father_num = (TextView) findViewById(R.id.tv_father_num);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_fees = (TextView) findViewById(R.id.tv_fees);
        tv_course = (TextView) findViewById(R.id.tv_course);
        tv_gender =  (TextView) findViewById(R.id.tv_gender);
        fees_update =  (TextView) findViewById(R.id.fees_update);

        tv_fees_status = (TextView) findViewById(R.id.tv_fees_status);
        tv_alt_mobile =  (TextView) findViewById(R.id.tv_alt_mobile);
        tv_shift = (TextView) findViewById(R.id.tv_shift);
        tv_class =  (TextView) findViewById(R.id.tv_class);
        tv_doj =  (TextView) findViewById(R.id.tv_doj);

        profile_image = (ImageView) findViewById(R.id.profile_image);

        edit_profile = (TextView) findViewById(R.id.edit_profile);



        Intent i = getIntent();
        final String id = i.getStringExtra("std_id");
        final String name = i.getStringExtra("name");
        final String father_name = i.getStringExtra("father_name");
         father_num = i.getStringExtra("father_num");
         address = i.getStringExtra("address");
         gender = i.getStringExtra("gender");
         course = i.getStringExtra("course");
         fees = i.getStringExtra("fees");

        String fees_status = i.getStringExtra("fees_status");
        alt_mobile = i.getStringExtra("alt_mobile");
         classy = i.getStringExtra("class");
         shift = i.getStringExtra("shift");
        String doj = i.getStringExtra("doj");
        student_type = i.getStringExtra("student_type");

        tv_student_id.setText(id);
        tv_name.setText(name);
        tv_father.setText(father_name);
        tv_father_num.setText(father_num);
        tv_address.setText(address);
        tv_gender.setText(gender);
        tv_course.setText(course);
        tv_fees.setText(fees);

        tv_fees_status.setText(fees_status);
        tv_alt_mobile.setText(alt_mobile);
        tv_class.setText(classy);
        tv_shift.setText(shift);
        tv_doj.setText(doj);


        if(gender.equalsIgnoreCase("male")){

            profile_image.setImageResource(R.drawable.shoaib);
        }
        else if(gender.equalsIgnoreCase("female")){
            profile_image.setImageResource(R.drawable.girl);
        }


        fees_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(ProfileActivity.this,PaymentActivity.class);
                i.putExtra("stdid",id);
                i.putExtra("name",name);

                startActivity(i);
            }
        });

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent  = new Intent(ProfileActivity.this,EditProfileActivity.class);

                intent.putExtra("stdid",id);
                intent.putExtra("name",name);
                intent.putExtra("father",father_name);
                intent.putExtra("father_num",father_num);
                intent.putExtra("alt_mobile",alt_mobile);
                intent.putExtra("address",address);
                intent.putExtra("gender",gender);
                intent.putExtra("course",course);
                intent.putExtra("fees",fees);
                intent.putExtra("student_type",student_type);
                intent.putExtra("class",classy);
                intent.putExtra("shift",shift);

                startActivity(intent);

            }
        });




        //-------****CODE FOR ALERT DAILOG IS NICE****--------Working Fine//

       /* fees_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.alertdialog_custom_view);
                Button dialogButton = (Button) dialog.findViewById(R.id.btn_submit);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Dismissed..!!",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }


        });*/




    }


}
