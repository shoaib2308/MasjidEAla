package simplycodighub.masjid_e_ala.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import simplycodighub.masjid_e_ala.R;

public class Main2Activity extends AppCompatActivity {
    TextView student_attend_shift0, student_attend_shift1,student_attend_shift2,student_attend_shift3,student_attend_shift_afterFarz,student_attend_shift_afterIsha;
    LinearLayout add_student,all_student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        add_student = (LinearLayout) findViewById(R.id.add_student);
        all_student= (LinearLayout) findViewById(R.id.all_student);
        student_attend_shift_afterFarz = (TextView) findViewById(R.id.shift_afterFarz);
        student_attend_shift0 = (TextView) findViewById(R.id.shift0);
        student_attend_shift1= (TextView) findViewById(R.id.shift1);
        student_attend_shift2 = (TextView) findViewById(R.id.shift2);
        student_attend_shift3= (TextView) findViewById(R.id.shift3);
        student_attend_shift_afterIsha = (TextView) findViewById(R.id.shift_afterIsha);

        add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent i = new Intent(Main2Activity.this,AddStudentActivity.class);
                startActivity(i);


            }
        });
        all_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Main2Activity.this,AllStudentActivity.class);
                startActivity(i);
            }
        });
        student_attend_shift_afterFarz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent i = new Intent(Main2Activity.this,ShiftFourActivity.class);
                //startActivity(i);
                showBottomSheetDialog();
            }
        });
        student_attend_shift0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent i = new Intent(Main2Activity.this,ShiftZeroActivity.class);
               // startActivity(i);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Main2Activity.this);
                // Setting Alert Dialog Title
                alertDialogBuilder.setTitle("Confirm Exit..!!!");
                // Icon Of Alert Dialog
                alertDialogBuilder.setIcon(R.drawable.ic_add_box_black_24dp);
                // Setting Alert Dialog Message
                alertDialogBuilder.setMessage("Are you sure,You want to exit");
                alertDialogBuilder.setCancelable(false);

                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Main2Activity.this,"You clicked over No",Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"You clicked on Cancel",Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        student_attend_shift1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Main2Activity.this,ShiftOneActivity.class);
                startActivity(i);
            }
        });
        student_attend_shift2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Main2Activity.this,ShiftTwoActivity.class);
                startActivity(i);
            }
        });
        student_attend_shift3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Main2Activity.this,ShiftThreeActivity.class);
                startActivity(i);
            }
        });
        student_attend_shift_afterIsha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Main2Activity.this,ShiftFiveActivity.class);
                startActivity(i);
            }
        });

    }

    public void showBottomSheetDialog() {
        View view = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_dialog, null);

        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(view);
        dialog.show();
    }
}
