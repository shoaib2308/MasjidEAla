package simplycodighub.masjid_e_ala.View;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import simplycodighub.masjid_e_ala.R;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    private CoordinatorLayout coordinatorLayout;
    Context context;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                checkConnection();
            }
        }, SPLASH_TIME_OUT);
    }


    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    public void checkConnection() {
        if (isOnline()) {
            Toast.makeText(SplashScreen.this, "You are connected to Internet", Toast.LENGTH_SHORT).show();
             Intent i = new Intent(SplashScreen.this,LockActivity.class);
             startActivity(i);

             finish();

        } else {
            Toast.makeText(SplashScreen.this, "No Internet", Toast.LENGTH_SHORT).show();

        }

    }



}