package simplycodighub.masjid_e_ala.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import simplycodighub.masjid_e_ala.R;

import static android.widget.Toast.LENGTH_LONG;

public class LockActivity extends AppCompatActivity {

    EditText ed_pass;


    Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);

        ed_pass = (EditText) findViewById(R.id.password);

        btn_submit = (Button) findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ed_pass.getText().toString().equals("4686")) {

                    Toast.makeText(LockActivity.this, "Welcome To Admindashboard", LENGTH_LONG).show();

                   // Intent i = new Intent(LockActivity.this,MainActivity.class);
                    Intent i = new Intent(LockActivity.this,Main2Activity.class);
                    startActivity(i);
                    finish();


                }
                else {

                    Toast.makeText(LockActivity.this, "Wrong Password", LENGTH_LONG).show();

                }



            }
        });
    }


}
