package edu.temple.goalie.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CategoryAdapter extends BaseAdapter {


    private Context context;
    private String[] categoryList;

    public CategoryAdapter(Context context, String[] categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }


    @Override
    public int getCount() {
        return categoryList.length;
    }

    @Override
    public Object getItem(int position) {
        return categoryList[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView colorView = new TextView(this.context);
        colorView.setText(categoryList[position]);
        colorView.setTextSize(20);
        colorView.setTextColor(Color.BLACK);
        return colorView;
    }
}