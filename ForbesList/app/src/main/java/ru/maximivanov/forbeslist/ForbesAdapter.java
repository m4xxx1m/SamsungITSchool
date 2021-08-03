package ru.maximivanov.forbeslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ForbesAdapter extends ArrayAdapter<Person> {

    public ForbesAdapter(@NonNull Context context, @NonNull ArrayList<Person> objects) {
        super(context, R.layout.list_view_item, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Person person = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, null);
        }
        ((ImageView)convertView.findViewById(R.id.flag)).setImageResource(person.flag_res);
        ((TextView)convertView.findViewById(R.id.name)).setText(person.name);
        ((TextView)convertView.findViewById(R.id.money)).setText(person.money);
        return convertView;
    }
}
