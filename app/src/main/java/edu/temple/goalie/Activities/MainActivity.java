package edu.temple.goalie.Activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

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
        list = (ListView)findViewById(R.id.goalList);
        mDBHelper = new DBHelper(this);
        db= mDBHelper.getWritableDatabase();

        String[] from = {mDBHelper.TITLE, mDBHelper.CATEGORY, mDBHelper.DESCRIPTION,
                        mDBHelper.STARTDAY.toString(), mDBHelper.STARTMONTH.toString(), mDBHelper.STARTYEAR.toString(),
                        mDBHelper.ENDDAY.toString(), mDBHelper.ENDMONTH.toString(), mDBHelper.ENDYEAR.toString() };

        final String[] column = {mDBHelper.C_ID, mDBHelper.TITLE, mDBHelper.CATEGORY, mDBHelper.DESCRIPTION,
                                mDBHelper.STARTDAY.toString(), mDBHelper.STARTMONTH.toString(), mDBHelper.STARTYEAR.toString(),
                                mDBHelper.ENDDAY.toString(), mDBHelper.ENDMONTH.toString(),mDBHelper.ENDYEAR.toString()};

        int[] to = {R.id.title, R.id.Detail, R.id.type, R.id.time, R.id.date};

        final Cursor cursor = db.query(mDBHelper.TABLE_NAME, column, null, null ,null, null, null);
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_entry, cursor, from, to, 0);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> listView, View view, int position,
                                    long id){
                Intent intent = new Intent(MainActivity.this, View_Note.class);
                intent.putExtra(getString(R.string.rodId), id);
                startActivity(intent);
            }

        });
    }
}
