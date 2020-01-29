package com.gestionsms.youssef.gestionsms;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Telephony;
import android.support.v4.content.ContextCompat;

import java.util.Vector;

public class SmsDao {
    private Database db;
    private SQLiteDatabase sqLiteDatabase;

    public SmsDao(Context context) {
        db = new Database(context);
        sqLiteDatabase = db.getWritableDatabase();
    }

    public void save() {
        Vector<Sms> smsVector = new Vector<>();
        Sms sms;

    }
}
