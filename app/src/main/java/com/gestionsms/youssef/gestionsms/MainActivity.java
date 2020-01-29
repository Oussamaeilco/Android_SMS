package com.gestionsms.youssef.gestionsms;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.Telephony;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    private ListView list;
    private SmsAdapter smsAdapter;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.show();

        if(checkSelfPermission(Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[] {Manifest.permission.READ_SMS}, 100);
        else{
            smsAdapter = new SmsAdapter(this, load());
            list.setAdapter(smsAdapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    intent = new Intent(getBaseContext(), SingleSmsActivity.class);
                    List<Sms> smsList = load();
                    Sms sms = smsList.get(position);
                    intent.putExtra("date", sms.getDate());
                    intent.putExtra("number", sms.getNumber());
                    intent.putExtra("message", sms.getMessage());
                    startActivity(intent);
                }
            });
        }
    }

    private List<Sms> load() {
        Vector<Sms> smsVector = new Vector<>();
        Sms sms;
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Cursor cursor = getContentResolver().query(Telephony.Sms.Inbox.CONTENT_URI, null, null, null, null);
            while (cursor.moveToNext()) {
                sms = new Sms();
                sms.setId(cursor.getString(cursor.getColumnIndex(Telephony.Sms.Inbox._ID)));
                sms.setDate(cursor.getString(cursor.getColumnIndex(Telephony.Sms.Inbox.DATE)));
                sms.setNumber(cursor.getString(cursor.getColumnIndex(Telephony.Sms.Inbox.ADDRESS)));
                sms.setMessage(cursor.getString(cursor.getColumnIndex(Telephony.Sms.Inbox.BODY)));

                smsVector.add(sms);
            }
        }

        return smsVector;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
