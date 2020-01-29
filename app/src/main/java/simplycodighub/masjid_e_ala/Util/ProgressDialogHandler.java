package simplycodighub.masjid_e_ala.Util;

import android.app.ProgressDialog;
import android.content.Context;

import simplycodighub.masjid_e_ala.R;

public class ProgressDialogHandler {

	private static ProgressDialog progressDialog = null;

	public static void showBusyScreen(Context context) {
        if (context == null) {
            return;
        }

		if (progressDialog != null) {
			hideBusyScreen();
		}


		progressDialog =  new ProgressDialog(context, R.style.ProgressBarStyle);
		progressDialog.setIndeterminate(false);
		progressDialog.setCancelable(false);

		progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
		progressDialog.show();
	}

	public static void hideBusyScreen() {
        if (progressDialog != null) {
            try {
                progressDialog.dismiss();
            }
            catch (IllegalArgumentException exception) {
				progressDialog = null;
            }
		}
	}
}
