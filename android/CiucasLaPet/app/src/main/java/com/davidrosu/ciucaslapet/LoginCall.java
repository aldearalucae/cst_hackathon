package com.davidrosu.ciucaslapet;

import android.os.AsyncTask;
import android.widget.Toast;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class LoginCall extends AsyncTask<String, String, String> {
    public boolean success = false;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String string = params[0];

        try {
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");

            RequestBody body = RequestBody.create(JSON, string);

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("http://geamana.go.ro:80/api/login")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.code() == 200) {
                this.success = true;
            } else {
                this.success = false;
            }

            return response.body().string();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}