package edu.temple.goalie.Activities;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import edu.temple.goalie.Adapter.CategoryAdapter;
import edu.temple.goalie.Database.DBHelper;
import edu.temple.goalie.R;

public class CreateGoal extends AppCompatActivity {

    public static final String[] CATEGORY = {"Educational","Physical Health","Financial","Mental Health"};

    TextView setStartDate;
    TextView setEndDate;
    private DatePickerDialog.OnDateSetListener mStartDateSetListener;
    private DatePickerDialog.OnDateSetListener mEndDateSetListener;
    SQLiteDatabase db;
    DBHelper mDBHelper;
    int startDay, startMonth, startYear, endDay, endMonth, endYear;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_goal);

        //Set everything
        CategoryAdapter categoryAdapter = new CategoryAdapter(this,CATEGORY);
        Spinner categorySpinner = findViewById(R.id.categorySpinner);
        categorySpinner.setAdapter(categoryAdapter);

        final EditText editName = findViewById(R.id.editGoalName);
        final EditText editDescription = findViewById(R.id.editDescription);
        Button createGoalButton = findViewById(R.id.createGoalButton);

        //Set adapter listener
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = CATEGORY[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Set start date
        setStartDate = (TextView) findViewById(R.id.editStartDate);
        setStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(CreateGoal.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mStartDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mStartDateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                startDay = day;
                startMonth = month;
                startYear = year;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                setStartDate.setText(date);
            }
        };


        //Set end date
        setEndDate = (TextView) findViewById(R.id.editEndDate);
        setEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(CreateGoal.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mEndDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mEndDateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                endDay = day;
                endMonth = month;
                endYear = year;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                setEndDate.setText(date);
            }
        };

        createGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editName.getText().toString().isEmpty() || editDescription.getText().toString().isEmpty()){
                    Toast.makeText(CreateGoal.this, "One field is empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    ContentValues cv = new ContentValues();
                    cv.put(mDBHelper.TITLE, editName.getText().toString());
                    cv.put(mDBHelper.CATEGORY,category);
                    cv.put(mDBHelper.DESCRIPTION, editDescription.getText().toString());
                    cv.put(mDBHelper.STARTDAY, startDay);
                    cv.put(mDBHelper.STARTMONTH,startMonth);
                    cv.put(mDBHelper.STARTYEAR,startYear);
                    cv.put(mDBHelper.ENDDAY,endDay);
                    cv.put(mDBHelper.ENDMONTH,endMonth);
                    cv.put(mDBHelper.ENDYEAR,endYear);
                    Intent toMainIntent = new Intent(CreateGoal.this,MainActivity.class);
                    startActivity(toMainIntent);

                }
            }
        });
    }
}
