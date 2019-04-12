package edu.temple.goalie.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import edu.temple.goalie.R;

public class GoalListAdapter extends SimpleCursorAdapter {
    public GoalListAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
        //ImageView goalImage = view.findViewById(R.id.goalImage);
    }

}
