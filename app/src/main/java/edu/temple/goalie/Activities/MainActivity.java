package edu.temple.goalie.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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


        FloatingActionButton createNoteButton = findViewById(R.id.createNoteButton);
        createNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toCreateNoteActivity = new Intent(MainActivity.this, CreateGoal.class);
                startActivity(toCreateNoteActivity);
            }
        });


        //Set list view
        list = findViewById(R.id.goalList);
        mDBHelper = new DBHelper(this);
        db= mDBHelper.getWritableDatabase();

        String[] from = {mDBHelper.TITLE, mDBHelper.CATEGORY, mDBHelper.DESCRIPTION,
                        mDBHelper.STARTDAY.toString(), mDBHelper.STARTMONTH.toString(), mDBHelper.STARTYEAR.toString(),
                        mDBHelper.ENDDAY.toString(), mDBHelper.ENDMONTH.toString(), mDBHelper.ENDYEAR.toString() };

        final String[] column = {mDBHelper.ID, mDBHelper.TITLE, mDBHelper.CATEGORY, mDBHelper.DESCRIPTION,
                                mDBHelper.STARTDAY.toString(), mDBHelper.STARTMONTH.toString(), mDBHelper.STARTYEAR.toString(),
                                mDBHelper.ENDDAY.toString(), mDBHelper.ENDMONTH.toString(),mDBHelper.ENDYEAR.toString()};

        int[] to = {R.id.title, R.id.category, R.id.description, R.id.startDay, R.id.startMonth, R.id.startYear,
                    R.id.endDay, R.id.endMonth, R.id.endYear};

        Cursor cursor = db.query(mDBHelper.TABLE_NAME, column, null, null ,null, null, null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_entry, cursor, from, to, 0);

        list.setAdapter(adapter);
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            public void onItemClick(AdapterView<?> listView, View view, int position,
//                                    long id){
//                Intent intent = new Intent(MainActivity.this, View_Note.class);
//                intent.putExtra(getString(R.string.rodId), id);
//                startActivity(intent);
//            }
//
//        });
    }
}
