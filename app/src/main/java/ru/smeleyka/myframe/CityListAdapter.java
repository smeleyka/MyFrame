package ru.smeleyka.myframe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by smeleyka on 27.09.17.
 */

public class CityListAdapter<T> extends BaseAdapter {
    Context ctx;
    LayoutInflater mInflater;
    ArrayList<T> objects;

    public CityListAdapter(Context ctx, ArrayList<T> objects) {
        this.ctx = ctx;
        this.mInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {

        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = mInflater.inflate(R.layout.fragment_layout, null);
        }
        City city = (City)getItem(position);

        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
        imageView.setImageResource(city.getImageResourceId());

        TextView text = (TextView) view.findViewById(R.id.frame_textV);
        text.setText(city.getCityName()+ " " + position);



        return view;
    }


}
