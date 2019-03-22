package edu.temple.goalie.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.EventLogTags;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "goals";
    public static final String TABLE_NAME = "goalTable";
    public static final String C_ID = "id";
    public static final String TITLE = "title";
    public static final String CATEGORY = "type";
    public static final String DESCRIPTION = "description";
    public static final String DAY = "day";
    public static final String MONTH = "month";
    public static final String YEAR = "year";

    public static final int VERSION = 2;

    private final String createDB = "create table if not exists " + TABLE_NAME + " ( "
            + C_ID + " integer primary key autoincrement, "
            + TITLE + " text, "
            + CATEGORY + " text, "
            + DESCRIPTION + " text, "
            + DAY + " int, "
            + MONTH + " int, "
            + YEAR + " int)";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + TABLE_NAME);
    }
}