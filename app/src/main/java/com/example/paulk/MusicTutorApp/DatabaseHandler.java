package com.example.paulk.MusicTutorApp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulk on 28/06/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    private SQLiteDatabase dBase;

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
            + KEY_IMAGE + " BLOB, "
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

        dBase = db;

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
        addQuestions();
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
    }

    public void incrementLevel(int userID){
        SQLiteDatabase db = this.getWritableDatabase();

        Log.i("TAG", "Level incremented!");
        String strSQL = "UPDATE " + TABLE_USER + " SET levelID = levelID + 1 WHERE userID = " + userID;
        db.execSQL(strSQL);
    }



    public void addScore(int score, int level, int userID) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.i("ADD SCORE", "Add score sucessfully accessed");
        ContentValues values = new ContentValues();
        values.put(KEY_SCORE, score);
        values.put(KEY_LEVEL, level);
        values.put(KEY_SCOREUSERID, userID);

        // Inserting Row
        db.insert(TABLE_SCORE, null, values);
    }


    public int getHighScores(int userID, int level) {

        int score = 0;
        SQLiteDatabase db = this.getReadableDatabase();
       String selectQuery = "SELECT MAX" +
               "("+ KEY_SCORE +") AS highScore FROM "
               + TABLE_SCORE + " where "
               + KEY_SCOREUSERID
               + " = "
               + userID
               + " AND "
               + KEY_LEVEL
               + " = "
               + level;

        //String selectQuery = "SELECT MAX(score) AS highScore FROM score where userID = 1 AND LEVEL = 1";
        Cursor c = db.rawQuery(selectQuery, null);
        c.moveToFirst();
        score = c.getInt(c.getColumnIndex("highScore"));

        c.close();
        return score;
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

    public int getUserID(String username) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, null, " USERNAME=?",
                new String[] { username }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return -1;
        }
        cursor.moveToFirst();
        int userID = cursor.getInt(cursor.getColumnIndex(KEY_USERID));
        cursor.close();
        return userID;
    }



    public int getLevelID(int userID) {

        int levelID = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + KEY_USERLEVELID + " FROM " + TABLE_USER + " WHERE "
                + KEY_USERID + " = " + userID;

        Cursor c = db.rawQuery(selectQuery, null);
        c.moveToFirst();
        levelID = c.getInt(c.getColumnIndex(KEY_USERLEVELID));

        c.close();
        return levelID;
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

      /*  SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, null, " USERNAME=?",
                new String[] { username }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return 0;
        }
        cursor.moveToFirst();
        int currentLevel = cursor.getInt(cursor.getColumnIndex(KEY_USERLEVELID));
        cursor.close();
        return currentLevel;*/
    }

    public void addQuestions() {


        Bitmap image;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        byte imageInByte[];

       // Text Questions
        Question q1=new Question("Where does the sound come out of on the body of an acoustic guitar?",
                "Fretboard", "Neck", "Pickguard", "Sound hole", "Sound hole", 1);
        this.addQuestion(q1);
        Question q2=new Question("What part of the guitar holds the strings firmly on the body?",
                "Neck", "Pickguard", "Bridge", "Pick", "Bridge", 1);
        this.addQuestion(q2);
        Question q3=new Question("What is the 5th string usually tuned to in standard tuning?",
                "A", "B", "G", "D", "A", 1);
        this.addQuestion(q3);
        Question q4=new Question("What are the thin metal strips on the fretboard called?",
                "Nuts", "Pins", "Strap buttons", "Frets", "Frets", 1);
        this.addQuestion(q4);
        Question q5=new Question("What part protects the body from wear and tear of strumming the guitar?",
                "Bridge", "Pick", "Pickguard", "Neck", "Pickguard", 1);
        this.addQuestion(q5);
        Question q6=new Question("What must you unwind to take the strings off a guitar?",
                "Nuts", "Body", "Neck", "Tuners", "Tuners", 2);
        this.addQuestion(q6);
        Question q7=new Question("What do the dots mean on the fretboard?",
                "They indicate specific fret numbers", "They are broken", "They are there for style",
                "They tune the strings", "They indicate specific fret numbers", 2);
        this.addQuestion(q7);
        Question q8=new Question("What fret does the double dot usually appear?",
                "8th", "4th", "15th", "12th", "12th", 2);
        this.addQuestion(q8);
        Question q9=new Question("What are the horizontal lines on a chord chart?",
                "Strings", "Nuts", "Frets", "Bolts", "Frets", 2);
        this.addQuestion(q9);
        Question q10=new Question("What do the numbers on guitar tabs mean?",
                "Fret number", "Neck size", "Nut size", "Body size", "Fret number", 2);
        this.addQuestion(q10);
       Question q11=new Question("Where does your 1st finger go in a D Major chord?",
                "2nd fret of the 3rd string", "4th fret of the 2nd string", "1st fret of the 6th string",
                "2nd fret of the 4th string", "2nd fret of the 3rd string", 3);
        this.addQuestion(q11);
        Question q12=new Question("Where does your 1st finger go in a C Major chord?",
                "2nd fret of the 3rd string", "4th fret of the 2nd string", "1st fret of the 2nd string",
                "2nd fret of the 4th string", "1st fret of the 2nd string", 3);
        this.addQuestion(q12);
        Question q13=new Question("Where does your 3rd finger go in a G Major chord?",
                "2nd fret of the 3rd string", "4th fret of the 2nd string",
                "1st fret of the 2nd string", "3rd fret of the 1st string",
                "3rd fret of the 1st string", 3);
        this.addQuestion(q13);
        Question q14=new Question("Where does your 3rd finger go in A Minor chord?","2nd fret of the 3rd string",
                "4th fret of the 2nd string", "1st fret of the 2nd string", "3rd fret of the 1st string", "2nd fret of the 3rd string", 3);
        this.addQuestion(q14);
        Question q15=new Question("Where does your 2nd finger go in E Minor chord?","4th fret of the 2nd string",
                "1st fret of the 2nd string", "2nd fret of the 5th string",
                "3rd fret of the 1st string", "2nd fret of the 5th string", 3);
        this.addQuestion(q15);
        Question q16=new Question("Where should you rest your elbow when strumming?",
                "Neck", "Headstock", "On the Fret", "Top of the body", "Top of the body", 4);
        this.addQuestion(q16);
        Question q17=new Question("What scale is made up of semi tone notes and has 12 notes?",
                "Major", "Bridge", "Chromatic", "Interval", "Chromatic", 4);
        this.addQuestion(q17);
        Question q18=new Question("What type of chords have a heavy sound and will mostly be heard on an electric with distortion?",
                "Neck", "Power", "Table", "Strap", "Power", 4);
        this.addQuestion(q18);
        Question q19=new Question("How many notes are in a major scale?",
                "6", "7", "8", "9", "7", 4);
        this.addQuestion(q19);
        Question q20=new Question("How many notes does the chromatic scale have?",
                "10", "11", "12", "13", "13", 4);
        this.addQuestion(q20);
        Question q21=new Question("What is the technique where the guitarist mutes the strings just in front of the bridge?",
                "String mute", "Palm mute", "Power mute", "Bend mute", "Palm mute", 5);
        this.addQuestion(q21);
        Question q22=new Question("What technique involves playing a note and moving your finger to a different fret whilst keeping the finger firmly pressed against the fretboard as you move?",
                "Mute", "Slide", "Bend", "Power", "Slide", 5);
        this.addQuestion(q22);
        Question q23=new Question("What technique involves a wavering/wobbling effect from sudden changes in pitch?",
                "Mute", "Slide", "Vibrato", "Power", "Vibrato", 5);
        this.addQuestion(q23);
        Question q24=new Question("What is the chordal technique where the plectrum or thumb is rolled across the strings slowly to articulate each individual note of the chord?",
                "Power chord", "Bend", "Vibrato", "Arpeggiated chord", "Arpeggiated chord", 5);
        this.addQuestion(q24);
        Question q25=new Question("What technique is achieved by using a fretting hand finger to slam down onto a fret to sound a note without the need for plucking?",
                "Pull off", "Hammer on", "Bend", "Vibrato", "Hammer on", 5);
        this.addQuestion(q25);

        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.parts_of_acoustic_hstockgone);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();

        Question q26=new Question("Name the missing label in this image:",
                "Bridge", "Pick", "Neck", "Headstock", "Headstock", 1, imageInByte);
        this.addImageQuestion(q26);



        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.parts_of_electric_tunersgone);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        //Image questions
        Question q27=new Question("Name the missing label in this image:",
                "Nuts", "Frets", "Tuners", "Neck", "Tuners", 1, imageInByte);
        this.addImageQuestion(q27);



        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.parts_of_electric_fretsgone);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q28=new Question("Name the missing label in this image:",
                "Nuts", "Frets", "Tuners", "Neck", "Frets", 1, imageInByte);
        this.addImageQuestion(q28);

        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.parts_of_acoustic_bridgegone);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q29=new Question("Name the missing label in this image:",
                "Bridge", "Pick", "Pickguard", "Neck", "Bridge", 1, imageInByte);
        this.addImageQuestion(q29);


        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.parts_of_acoustic_pguardgone);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q30=new Question("Name the missing label in this image:",
                "Bridge", "Pick", "Pickguard", "Neck", "Pickguard", 1, imageInByte);
        this.addImageQuestion(q30);

        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.fretboard_9thfret);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q31=new Question("Name the fret in this image:",
                "4th fret", "7th fret", "10th fret ", "9th fret", "9th fret", 2, imageInByte);
        this.addImageQuestion(q31);

        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.fretboard_5thfret);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q32=new Question("Name the fret in this image:",
                "3rd fret", "7th fret", "5th fret","12th fret", "5th fret", 2, imageInByte);
        this.addImageQuestion(q32);

        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.finger_num_4th);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q33=new Question("What finger number is this?",
                "1", "2", "3", "4", "4", 2, imageInByte);
        this.addImageQuestion(q33);

        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.tab_lesson_1);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q34=new Question("What kind of musical notation is this?",
                "Tab", "Treble", "Rock", "Jazz", "Tab", 2, imageInByte);
        this.addImageQuestion(q34);

        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.tab_lesson_1);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q35=new Question("What do the x’s mean in this picture?",
                "The note should not be played", "The string should be tapped",
                "The string should be strummed lightly", "The string should be strummed hard",
                "The note should not be played", 2, imageInByte);
        this.addImageQuestion(q35);


        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.g_major_chord_gone);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q36=new Question("What is the name of this chord?",
                "D Major", "G Major", "A Minor", "E Minor", "G Major", 3, imageInByte);
        this.addImageQuestion(q36);

        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.d_major_chord_gone);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q37=new Question("What is the name of this chord?",
                "D Major", "G Major", "A Minor", "E Minor", "D Major", 3, imageInByte);
        this.addImageQuestion(q37);

        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.c_major_chord_gone);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q38=new Question("What is the name of this chord?",
                "D Major", "A Minor", "E Minor", "C Major", "C Major", 3, imageInByte);
        this.addImageQuestion(q38);


        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.e_minor_chord_gone);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q39=new Question("What is the name of this chord?",
                "D Major", "A Minor", "E Minor", "C Major", "E Minor", 3, imageInByte);
        this.addImageQuestion(q39);

        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.a_minor_chord_gone);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q40=new Question("What is the name of this chord?",
                "D Major", "A Minor", "E Minor", "C Major", "A Minor", 3, imageInByte);
        this.addImageQuestion(q40);

        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.power_chord);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q41=new Question("What type of chord is this?",
                "Power", "Chromatic", "Major", "Minor", "Power", 4, imageInByte);
        this.addImageQuestion(q41);


        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.c_major_scale);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q42=new Question("What type of scale is this?",
                "Major", "Power", "Chromatic", "Interval", "Major", 4, imageInByte);
        this.addImageQuestion(q42);

        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.c_major_scale);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q43=new Question("What scale is this?",
                "C Major", "C Chromatic", "D Power", "G Minor", "C Major", 4, imageInByte);
        this.addImageQuestion(q43);

        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.note_intervals_gone);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q44=new Question("What is the name of the gaps between the notes?",
                "Nuts", "Intervals", "Powers", "Majors", "Intervals", 4, imageInByte);
        this.addImageQuestion(q44);


        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.note_intervals_bc_gone);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q45=new Question("What size is the interval between B and C?",
                "Half step", "Whole step", "Power step", "Slow step", "Half step", 4, imageInByte);
        this.addImageQuestion(q45);


        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.slides);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q46=new Question("What is the name of this technique?",
                "Vibrato", "Bend", "Hammer-on", "Slide", "Slide", 5, imageInByte);
        this.addImageQuestion(q46);


        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.pulloff);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q47=new Question("What is the name of this technique?",
                "Vibrato", "Pull-off", "Hammer-on", "Slide", "Pull-off", 5, imageInByte);
        this.addImageQuestion(q47);

        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.vibrato);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q48=new Question("What is the name of this technique?",
                "Vibrato", "Bend", "Bend", "Slide", "Vibrato", 5, imageInByte);
        this.addImageQuestion(q48);

        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.hammeron);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q49=new Question("What is the name of this technique?",
                "Vibrato", "Bend", "Hammer-on", "Slide", "Hammer-on", 5, imageInByte);
        this.addImageQuestion(q49);

        image = BitmapFactory.decodeResource(MyMusicTutorApp.getAppContext().getResources(),R.drawable.bends);
        stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageInByte = stream.toByteArray();
        Question q50=new Question("What is the name of this technique?",
                "Pull-off", "Hammer-on", "Bend", "Vibrato", "Bend", 5, imageInByte);
        this.addImageQuestion(q50);




        Log.i("AYE", "Questions added!");
    }

    public void addQuestion(Question question){

        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION, question.getQuestion());
        values.put(KEY_ANSWER1, question.getA1());
        values.put(KEY_ANSWER2, question.getA2());
        values.put(KEY_ANSWER3, question.getA3());
        values.put(KEY_ANSWER4, question.getA4());
        values.put(KEY_CORRECT, question.getCorrect());
        values.put(KEY_QUESTIONLEVELID, question.getLevelID());

        dBase.insert(TABLE_TESTQUESTION, null, values);

    }

    public void addImageQuestion(Question question){

        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION, question.getQuestion());
        values.put(KEY_IMAGE, question.getImageResource());
        values.put(KEY_ANSWER1, question.getA1());
        values.put(KEY_ANSWER2, question.getA2());
        values.put(KEY_ANSWER3, question.getA3());
        values.put(KEY_ANSWER4, question.getA4());
        values.put(KEY_CORRECT, question.getCorrect());
        values.put(KEY_QUESTIONLEVELID, question.getLevelID());

        dBase.insert(TABLE_TESTIMAGEQUESTION, null, values);

    }

    public List<Question> getAllQuestions(int level) {
        List<Question> quesList = new ArrayList<Question>();
// Select All Query
        String selectQueryImageQuestions = "SELECT * FROM " + TABLE_TESTIMAGEQUESTION + " WHERE levelID = " + level;
        dBase=this.getReadableDatabase();
        Cursor cursor = dBase.rawQuery(selectQueryImageQuestions, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setQuestionID(cursor.getInt(0));
                quest.setImageResource(cursor.getBlob(1));
                quest.setQuestion(cursor.getString(2));
                quest.setA1(cursor.getString(3));
                quest.setA2(cursor.getString(4));
                quest.setA3(cursor.getString(5));
                quest.setA4(cursor.getString(6));
                quest.setCorrect(cursor.getString(7));
                quest.setIsImageQuestion(true);
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        cursor.close();
// return quest list


        String selectQueryQuestions = "SELECT * FROM " + TABLE_TESTQUESTION + " WHERE levelID = " + level;

        Cursor cursor2 = dBase.rawQuery(selectQueryQuestions, null);
// looping through all rows and adding to list
        if (cursor2.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setQuestionID(cursor2.getInt(0));
                quest.setQuestion(cursor2.getString(1));
                quest.setA1(cursor2.getString(2));
                quest.setA2(cursor2.getString(3));
                quest.setA3(cursor2.getString(4));
                quest.setA4(cursor2.getString(5));
                quest.setCorrect(cursor2.getString(6));
                quest.setIsImageQuestion(false);
                quesList.add(quest);
            } while (cursor2.moveToNext());
        }
        cursor2.close();




        return quesList;
    }
}
