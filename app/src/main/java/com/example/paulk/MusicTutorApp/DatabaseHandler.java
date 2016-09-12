package com.example.paulk.MusicTutorApp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by paulk on 28/06/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "musicTutorDatabase.db";

    // Table Names
    private static final String TABLE_USER = "user";
    private static final String TABLE_LEVEL = "level";
    private static final String TABLE_TESTQUESTION = "testQuestion";
    private static final String TABLE_SCORE = "score";
    private static final String TABLE_TESTIMAGEQUESTION = "testImageQuestion";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    // USER Table - column names
    private static final String KEY_USERID = "userID";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_USERLEVELID = "levelID";

    // LEVEL Table - column names
    private static final String KEY_LEVELID = "levelID";
    private static final String KEY_LEVELNAME = "levelName";

    // TESTQUESTION and IMAGETESTQUESTION Table - column names
    private static final String KEY_QUESTIONID = "questionID";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_IMAGEQID = "imageQID";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_ANSWER1 = "a1";
    private static final String KEY_ANSWER2 = "a2";
    private static final String KEY_ANSWER3 = "a3";
    private static final String KEY_ANSWER4 = "a4";
    private static final String KEY_CORRECT = "correct";
    private static final String KEY_QUESTIONLEVELID = "levelID";

    // SCORE Table - column names
    private static final String KEY_SCOREID = "scoreID";
    private static final String KEY_SCORE = "score";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_SCOREUSERID = "userID";


    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_USERNAME + " TEXT, "
            + KEY_PASSWORD + " TEXT, "
            + KEY_USERLEVELID + " INTEGER, "
            + "FOREIGN KEY ("+KEY_USERLEVELID+") REFERENCES "+TABLE_LEVEL+"("+KEY_LEVELID+"))";

    // Tag table create statement
    private static final String CREATE_TABLE_LEVEL = "CREATE TABLE " + TABLE_LEVEL
            + "(" + KEY_LEVELID + " INTEGER PRIMARY KEY,"
            + KEY_LEVELNAME + " TEXT)";

    // todo_tag table create statement
    private static final String CREATE_TABLE_TESTQUESTION = "CREATE TABLE "
            + TABLE_TESTQUESTION + "(" + KEY_QUESTIONID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_QUESTION + " TEXT, "
            + KEY_ANSWER1 + " TEXT, "
            + KEY_ANSWER2 + " TEXT, "
            + KEY_ANSWER3 + " TEXT, "
            + KEY_ANSWER4 + " TEXT, "
            + KEY_CORRECT + " TEXT, "
            + KEY_QUESTIONLEVELID + " INTEGER, "
            + "FOREIGN KEY ("+KEY_QUESTIONLEVELID+") REFERENCES "+TABLE_LEVEL+"("+KEY_LEVELID+"))";

    private static final String CREATE_TABLE_TESTIMAGEQUESTION = "CREATE TABLE "
            + TABLE_TESTIMAGEQUESTION + "(" + KEY_IMAGEQID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_IMAGE + " INTEGER, "
            + KEY_QUESTION + " TEXT, "
            + KEY_ANSWER1 + " TEXT, "
            + KEY_ANSWER2 + " TEXT, "
            + KEY_ANSWER3 + " TEXT, "
            + KEY_ANSWER4 + " TEXT, "
            + KEY_CORRECT + " TEXT, "
            + KEY_QUESTIONLEVELID + " INTEGER, "
            + "FOREIGN KEY ("+KEY_QUESTIONLEVELID+") REFERENCES "+TABLE_LEVEL+"("+KEY_LEVELID+"))";

    private static final String CREATE_TABLE_SCORE = "CREATE TABLE "
            + TABLE_SCORE + "(" + KEY_SCOREID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_SCORE + " INTEGER, "
            + KEY_LEVEL + " INTEGER, "
            + KEY_SCOREUSERID + " INTEGER, "
            + "FOREIGN KEY ("+KEY_SCOREUSERID+") REFERENCES "+TABLE_USER+"("+KEY_USERID+"))";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("TAG", "Constructor fired in Database handler!!!");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(CREATE_TABLE_LEVEL);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_TESTQUESTION);
        db.execSQL(CREATE_TABLE_TESTIMAGEQUESTION);
        db.execSQL(CREATE_TABLE_SCORE);
        db.execSQL("INSERT OR REPLACE INTO " + TABLE_LEVEL
                + "(" + KEY_LEVELID + "," + KEY_LEVELNAME + ") VALUES (1, 'complete beginner')");
        db.execSQL("INSERT OR REPLACE INTO " + TABLE_LEVEL
                + "(" + KEY_LEVELID + "," + KEY_LEVELNAME + ") VALUES (2, 'beginner')");
        db.execSQL("INSERT OR REPLACE INTO " + TABLE_LEVEL
                + "(" + KEY_LEVELID + "," + KEY_LEVELNAME + ") VALUES (3, 'novice')");
        db.execSQL("INSERT OR REPLACE INTO " + TABLE_LEVEL
                + "(" + KEY_LEVELID + "," + KEY_LEVELNAME + ") VALUES (4, 'Intermediate')");
        db.execSQL("INSERT OR REPLACE INTO " + TABLE_LEVEL
                + "(" + KEY_LEVELID + "," + KEY_LEVELNAME + ") VALUES (5, 'Expert')");
        Log.i("TAG", "OnCreate fired in Database handler!!! YAY!!!!!!!!!!!!!!");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TESTQUESTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TESTIMAGEQUESTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEVEL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);

        // create new tables
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        Log.i("TAG", "onOpen called!");
    }


    void addUser(String username, String password, int level) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, username);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_USERLEVELID, level);

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
    }

    public String getPassword(String username) {

        /*SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE "
                + KEY_USERNAME + " = " + username;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD));
        cursor.close();
        return password;*/

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, null, " USERNAME=?",
                new String[] { username }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD));
        cursor.close();
        return password;
    }

    public int getLevelID(String username) {

        /*SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE "
                + KEY_USERNAME + " = " + username;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD));
        cursor.close();
        return password;*/

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, null, " USERNAME=?",
                new String[] { username }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return 0;
        }
        cursor.moveToFirst();
        int currentLevel = cursor.getInt(cursor.getColumnIndex(KEY_USERLEVELID));
        cursor.close();
        return currentLevel;
    }

}
