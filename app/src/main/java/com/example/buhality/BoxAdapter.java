package com.example.buhality;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class BoxAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater LInflater;
    ArrayList<Char> objects;

    BoxAdapter(Context context, ArrayList<Char> chars){
        ctx = context;
        objects = chars;
        LInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

//колличество элеменов
    @Override
    public int getCount() {
        return objects.size();
    }

    //элемент по позиции
    @Override
    public Object getItem(int psition) {
        return objects.get(psition);
    }

    //позиция
    @Override
    public long getItemId(int position) {
        return position;
    }

//пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view = convertView;
        if (view == null){
            view = LInflater.inflate(R.layout.vivod_item, parent, false);
        }

        Char p =  getChar(position);
        ((TextView) view.findViewById(R.id.id)).setText(p.id);
        EditText ET1 = (EditText) view.findViewById(R.id.name);
        ET1.setText(p.name);
        EditText ET2 = (EditText) view.findViewById(R.id.mony);
        ET2.setText(p.mony);
        String q = p.rez;
        ((TextView) view.findViewById(R.id.rez)).setText(q);

        float x = p.rezy;
//
        if (x < 0 ) ((TextView) view.findViewById(R.id.rez)).setTextColor(Color.GREEN);
        if (x == 0 ) ((TextView) view.findViewById(R.id.rez)).setTextColor(Color.GRAY);
        if (x > 0 ) ((TextView) view.findViewById(R.id.rez)).setTextColor(Color.RED);





        return view;
    }

/////////////////////////

    public Char getChar(int position) {

        return (Char) getItem(position);
    }

}
