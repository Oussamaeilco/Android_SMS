package com.gestionsms.youssef.gestionsms;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SmsAdapter extends ArrayAdapter<Sms> {

    public SmsAdapter(@NonNull Context context, @NonNull List<Sms> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Sms sms = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }
        TextView date_v = convertView.findViewById(R.id.date);
        TextView number_v = convertView.findViewById(R.id.exped);
        TextView message_v = convertView.findViewById(R.id.message);

        long date = Long.parseLong(sms.getDate());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date_v.setText(sdf.format(new Date(date)));
        number_v.setText(sms.getNumber());
        message_v.setText(sms.getMessage());

        return convertView;
    }
}
