package edu.temple.goalie.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.temple.goalie.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent toCreateGoalIntent = new Intent(MainActivity.this,CreateGoal.class);
        startActivity(toCreateGoalIntent);
    }
}
