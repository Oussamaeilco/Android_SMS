package com.gestionsms.youssef.gestionsms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class SingleSmsActivity extends AppCompatActivity {
    Button export;
    String apiUrl = "http://192.168.43.212/backupmotor/public/api/stests";
    RequestQueue requestQueue;

    String number;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_sms);

        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        number = intent.getStringExtra("number");
        message = intent.getStringExtra("message");

        TextView date_v = findViewById(R.id.date);
        long d = Long.parseLong(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date_v.setText(sdf.format(new Date(d)));

        TextView number_v = findViewById(R.id.exped);
        number_v.setText(number);

        TextView message_v = findViewById(R.id.message);
        message_v.setText(message);

        export = findViewById(R.id.export);

        requestQueue = Volley.newRequestQueue(this);
    }

    public void apiStest(View v) {
        StringRequest postRequest = new StringRequest(Request.Method.POST, apiUrl,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                params.put("number", number);
                params.put("body", message);

                return params;
            }
        };
        requestQueue.add(postRequest);
    }
}
