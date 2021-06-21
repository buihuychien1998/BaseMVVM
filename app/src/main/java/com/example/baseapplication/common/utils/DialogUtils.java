package com.example.baseapplication.common.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.TextView;

import com.example.baseapplication.R;

import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;

public class DialogUtils {
    private static AlertDialog progressDialog;
    public static void buildProgressDialog(
        Context context,
        @LayoutRes int layout,
        boolean setCancellationOnTouchOutside
    ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        View customLayout =
//            getLayoutInflater().inflate(layout, null);
        builder.setView(layout);
        progressDialog = builder.create();
        progressDialog.setCanceledOnTouchOutside(setCancellationOnTouchOutside);
    }

    public static void showProgressDialog(Context context, String message) {
        dismissProgressDialog();
        buildProgressDialog(context, R.layout.dialog_progress, false);
        progressDialog.show();
        TextView tvMessage = progressDialog.findViewById(R.id.tv_message);
        tvMessage.setText(message);
    }

    public static void showProgressDialog(Context context, @StringRes int messageId) {
       showProgressDialog(context, context.getString(messageId));
    }

    public static void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
