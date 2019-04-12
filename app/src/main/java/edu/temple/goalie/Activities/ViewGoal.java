package edu.temple.goalie.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import edu.temple.goalie.Database.DBHelper;
import edu.temple.goalie.R;

public class ViewGoal extends AppCompatActivity {

    SQLiteDatabase db;
    DBHelper dbHelper;
    TextView viewGoalTitle;
    TextView viewGoalCategory;
    TextView viewGoalDescription;
    TextView viewGoalStartDay;
    TextView viewGoalStartMonth;
    TextView viewGoalStartYear;
    TextView viewGoalEndDay;
    TextView viewGoalEndMonth;
    TextView viewGoalEndYear;
    TextView viewGoalJournal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_goal);
        viewGoalTitle = findViewById(R.id.ViewGoalTitle);
        viewGoalCategory = findViewById(R.id.ViewGoalCategory);
        viewGoalDescription = findViewById(R.id.ViewGoalDescription);
        viewGoalStartDay = findViewById(R.id.ViewGoalStartDay);
        viewGoalStartMonth = findViewById(R.id.ViewGoalStartMonth);
        viewGoalStartYear = findViewById(R.id.ViewGoalStartYear);
        viewGoalEndDay = findViewById(R.id.ViewGoalEndDay);
        viewGoalEndMonth = findViewById(R.id.ViewGoalEndMonth);
        viewGoalEndYear = findViewById(R.id.ViewGoalEndYear);
        viewGoalJournal = findViewById(R.id.journal);

        final long id = getIntent().getExtras().getLong("goalIdSelected");

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + dbHelper.TABLE_NAME + " where " + dbHelper.ID + "=" + id, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                viewGoalTitle.setText(cursor.getString(cursor.getColumnIndex(dbHelper.TITLE)));
                viewGoalCategory.setText(cursor.getString(cursor.getColumnIndex(dbHelper.CATEGORY)));
                viewGoalDescription.setText(cursor.getString(cursor.getColumnIndex(dbHelper.DESCRIPTION)));
                viewGoalStartDay.setText(cursor.getString(cursor.getColumnIndex(dbHelper.STARTDAY)) + "/");
                viewGoalStartMonth.setText(cursor.getString(cursor.getColumnIndex(dbHelper.STARTMONTH)) + "/");
                viewGoalStartYear.setText(cursor.getString(cursor.getColumnIndex(dbHelper.STARTYEAR)));
                viewGoalEndDay.setText(cursor.getString(cursor.getColumnIndex(dbHelper.ENDDAY)) + "/");
                viewGoalEndMonth.setText(cursor.getString(cursor.getColumnIndex(dbHelper.ENDMONTH)) + "/");
                viewGoalEndYear.setText(cursor.getString(cursor.getColumnIndex(dbHelper.ENDYEAR)));
                viewGoalJournal.setText(cursor.getString(cursor.getColumnIndex(dbHelper.JOURNAL)));

                viewGoalStartDay.setTextSize(15);
                viewGoalStartMonth.setTextSize(15);
                viewGoalStartYear.setTextSize(15);
                viewGoalEndDay.setTextSize(15);
                viewGoalEndMonth.setTextSize(15);
                viewGoalEndYear.setTextSize(15);
            }
            cursor.close();
        }
        Toolbar toolbar = findViewById(R.id.createGoalToolbar);
        setSupportActionBar(toolbar);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_viewgoal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.deleteGoal:
                db.execSQL("DELETE FROM " + dbHelper.TABLE_NAME + " WHERE " + dbHelper.TITLE + "='" +
                        viewGoalTitle.getText() + "'");
                db.close();
                Intent intent = new Intent(ViewGoal.this, MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.editGoal:
                final Dialog setJournal = new Dialog(this);
                setJournal.setContentView(R.layout.dialog_set_journal);
                final EditText editJournal = setJournal.findViewById(R.id.editJournal);
                Cursor cursor2 = db.rawQuery("select * from " + dbHelper.TABLE_NAME + " where " + dbHelper.TITLE + " = '" + viewGoalTitle.getText() + "'" +
                        " and " + dbHelper.DESCRIPTION + " ='" + viewGoalDescription.getText() + "'", null);
                if (cursor2 != null) {
                    if (cursor2.moveToFirst()) {
                        editJournal.setText(cursor2.getString(cursor2.getColumnIndex(dbHelper.JOURNAL)));
                    }
                }
                Button saveJournal = setJournal.findViewById(R.id.saveJournal);
                Button dismissJournal = setJournal.findViewById(R.id.dismissJournal);

                saveJournal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.execSQL("UPDATE " + dbHelper.TABLE_NAME + " SET " + dbHelper.JOURNAL + "='" +
                                editJournal.getText().toString() + "' WHERE " + dbHelper.TITLE + "='" + viewGoalTitle.getText() +
                                "' and " + dbHelper.DESCRIPTION + "='" + viewGoalDescription.getText() + "'");
                        setJournal.dismiss();
                        ViewGoal.this.recreate();
                    }
                });

                dismissJournal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setJournal.dismiss();
                    }
                });
                setJournal.show();
                //return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
