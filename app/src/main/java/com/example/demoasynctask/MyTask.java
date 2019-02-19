package com.example.demoasynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

public class MyTask extends AsyncTask<Void, Integer, String> {
    private Context mconText;
    private TextView mtxtShow;
    private Button mbtnStart;
    private ProgressDialog mprogressDialog;

    MyTask(Context mconText, TextView mtxtShow, Button mbtnStart) {
        this.mconText = mconText;
        this.mtxtShow = mtxtShow;
        this.mbtnStart = mbtnStart;
    }

    @Override
    protected String doInBackground(Void... voids) {
        int i = 0;
        synchronized (this) {
            while (i < 10) {
                try {
                    wait(1500);
                    i++;
                    publishProgress(i);
                } catch (InterruptedException a) {
                    a.printStackTrace();
                }
            }
        }

        return R.string.download_complete;
    }

    @Override
    protected void onPreExecute() {
        mprogressDialog = new ProgressDialog(mconText);
        mprogressDialog.setTitle(R.string.downloading);
        mprogressDialog.setMax(10);
        mprogressDialog.setProgress(0);
        mprogressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mprogressDialog.show();
    }

    @Override
    protected void onPostExecute(String result) {
        mtxtShow.setText(result);
        mbtnStart.setEnabled(true);
        mprogressDialog.hide();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        mprogressDialog.setProgress(progress);
        mtxtShow.setText(R.string.downloading);
    }
}
