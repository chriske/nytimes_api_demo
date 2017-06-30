package hu.autsoft.nytimes.ui;


import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;

import hu.autsoft.nytimes.R;

public class BaseActivity extends AppCompatActivity {
    private MaterialDialog dialog;
    private MaterialDialog progressDialog;

    @Override
    protected void onStop() {
        super.onStop();

        removeDialog();
        removeProgressDialog();
    }

    protected void showDialog(String title, String message) {
        removeProgressDialog();
        if (dialog == null || !dialog.isShowing()) {

            MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                    .title(title)
                    .content(message)
                    .positiveText(getString(R.string.ok));

            dialog = builder.build();
            dialog.show();
        }
    }

    protected void removeDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    protected void showProgressDialog() {
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog = new MaterialDialog.Builder(this)
                    .title(R.string.loading)
                    .content(R.string.please_wait)
                    .progress(true, 0)
                    .show();
        }
    }

    protected void removeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
