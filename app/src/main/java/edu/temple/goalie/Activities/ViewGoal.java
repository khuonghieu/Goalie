package edu.temple.goalie.Activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import edu.temple.goalie.Database.DBHelper;
import edu.temple.goalie.R;

public class ViewGoal extends AppCompatActivity {

    SQLiteDatabase db;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_goal);
        TextView viewGoalTitle = findViewById(R.id.ViewGoalTitle);
        TextView viewGoalCategory = findViewById(R.id.ViewGoalCategory);
        TextView viewGoalDescription = findViewById(R.id.ViewGoalDescription);
        TextView viewGoalStartDay = findViewById(R.id.ViewGoalStartDay);
        TextView viewGoalStartMonth = findViewById(R.id.ViewGoalStartMonth);
        TextView viewGoalStartYear = findViewById(R.id.ViewGoalStartYear);
        TextView viewGoalEndDay = findViewById(R.id.ViewGoalEndDay);
        TextView viewGoalEndMonth = findViewById(R.id.ViewGoalEndMonth);
        TextView viewGoalEndYear = findViewById(R.id.ViewGoalEndYear);

        final long id = getIntent().getExtras().getLong("goalIdSelected");

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + dbHelper.TABLE_NAME + " where " + dbHelper.ID + "=" + id, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                viewGoalTitle.setText(cursor.getString(cursor.getColumnIndex(dbHelper.TITLE)));
                viewGoalCategory.setText(cursor.getString(cursor.getColumnIndex(dbHelper.CATEGORY)));
                viewGoalDescription.setText(cursor.getString(cursor.getColumnIndex(dbHelper.DESCRIPTION)));
                viewGoalStartDay.setText(cursor.getString(cursor.getColumnIndex(dbHelper.STARTDAY)));
                viewGoalStartMonth.setText(cursor.getString(cursor.getColumnIndex(dbHelper.STARTMONTH)));
                viewGoalStartYear.setText(cursor.getString(cursor.getColumnIndex(dbHelper.STARTYEAR)));
                viewGoalEndDay.setText(cursor.getString(cursor.getColumnIndex(dbHelper.ENDDAY)));
                viewGoalEndMonth.setText(cursor.getString(cursor.getColumnIndex(dbHelper.ENDMONTH)));
                viewGoalEndYear.setText(cursor.getString(cursor.getColumnIndex(dbHelper.ENDYEAR)));
            }
            cursor.close();
        }
    }
}
