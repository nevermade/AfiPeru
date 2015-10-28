package com.example.dp2.afiperu.util;

import android.app.ProgressDialog;
import android.os.AsyncTask;

/**
 * Created by Nevermade on 27/10/2015.
 */
public class GenericAsyncTask extends AsyncTask<Void,Void,Void >{


    AsyncTaskCallBack callback;

    public GenericAsyncTask(AsyncTaskCallBack callback) {
        super();

        this.callback=callback;

    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();

        Constants.PROGRESS.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        callback.onBackground();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Constants.PROGRESS.dismiss();
    }


}
