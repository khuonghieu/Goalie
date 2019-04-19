package edu.temple.goalie.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import edu.temple.goalie.Adapter.GoalListAdapter;
import edu.temple.goalie.Database.DBHelper;
import edu.temple.goalie.R;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    DBHelper mDBHelper;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set list view
        list = findViewById(R.id.goalList);
        mDBHelper = new DBHelper(this);
        db = mDBHelper.getWritableDatabase();

        Bundle bundle = getIntent().getExtras();
        String logInName = bundle.getString("accountName");
        TextView logInInfo = findViewById(R.id.logInInfo);
        logInInfo.setText(logInName);

        String[] from = {mDBHelper.TITLE, mDBHelper.CATEGORY,
                mDBHelper.STARTDAY, mDBHelper.STARTMONTH, mDBHelper.STARTYEAR,
                mDBHelper.ENDDAY, mDBHelper.ENDMONTH, mDBHelper.ENDYEAR};

        final String[] column = {mDBHelper.ID, mDBHelper.TITLE, mDBHelper.CATEGORY,

                mDBHelper.STARTDAY, mDBHelper.STARTMONTH, mDBHelper.STARTYEAR,
                mDBHelper.ENDDAY, mDBHelper.ENDMONTH, mDBHelper.ENDYEAR};

        int[] to = {R.id.title, R.id.category, R.id.startDay, R.id.startMonth, R.id.startYear,
                R.id.endDay, R.id.endMonth, R.id.endYear};

        Cursor cursor = db.query(mDBHelper.TABLE_NAME, column, null, null, null, null, null);
        GoalListAdapter adapter = new GoalListAdapter(this, R.layout.list_entry, cursor, from, to, 0);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View view, int position,
                                    long id) {
                Intent intent = new Intent(MainActivity.this, ViewGoal.class);
                intent.putExtra("goalIdSelected", id);
                startActivity(intent);
            }

        });
        Toolbar toolbar = findViewById(R.id.mainToolBar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final String[] sortedColumn = {mDBHelper.ID, mDBHelper.TITLE, mDBHelper.CATEGORY,
                mDBHelper.STARTDAY, mDBHelper.STARTMONTH, mDBHelper.STARTYEAR,
                mDBHelper.ENDDAY, mDBHelper.ENDMONTH, mDBHelper.ENDYEAR};
        String[] from = {mDBHelper.TITLE, mDBHelper.CATEGORY,
                mDBHelper.STARTDAY, mDBHelper.STARTMONTH, mDBHelper.STARTYEAR,
                mDBHelper.ENDDAY, mDBHelper.ENDMONTH, mDBHelper.ENDYEAR};
        int[] to = {R.id.title, R.id.category, R.id.startDay, R.id.startMonth, R.id.startYear,
                R.id.endDay, R.id.endMonth, R.id.endYear};

        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.addGoal:
                Intent intent = new Intent(MainActivity.this, CreateGoal.class);
                startActivity(intent);
                return true;
            case R.id.sortGoal:
                String orderByASC = DBHelper.STARTYEAR + ", " + DBHelper.STARTMONTH + ", " + DBHelper.STARTDAY + " ASC";
                Cursor cursorASC = db.query(mDBHelper.TABLE_NAME, sortedColumn, null, null, null, null,
                        orderByASC);
                SimpleCursorAdapter sortedAdapterASC = new SimpleCursorAdapter(this, R.layout.list_entry, cursorASC, from, to, 0);
                list.setAdapter(sortedAdapterASC);
                return true;
            case R.id.reverseSortGoal:
                String orderByDESC = DBHelper.STARTYEAR + ", " + DBHelper.STARTMONTH + ", " + DBHelper.STARTDAY + " DESC";
                Cursor cursorDESC = db.query(mDBHelper.TABLE_NAME, sortedColumn, null, null, null, null,
                        orderByDESC);
                SimpleCursorAdapter sortedAdapterDESC = new SimpleCursorAdapter(this, R.layout.list_entry, cursorDESC, from, to, 0);
                list.setAdapter(sortedAdapterDESC);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
