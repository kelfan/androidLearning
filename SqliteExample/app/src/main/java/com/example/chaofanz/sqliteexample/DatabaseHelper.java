package com.example.chaofanz.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaofanz on 15/11/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    //database
    private static final int DATABASE_VER=1;
    private static final String DATABASE_NAME="EMDTDev";

    //table
    private static final String TABLE_NAME="Persons";
    private static final String KEY_ID="Id";
    private static final String KEY_NAME="Name";
    private static final String KEY_EMAIL="Email";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null,DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("
                +KEY_ID+" INTEGER PRIMARY KEY, "
                +KEY_NAME+" TEXT, "
                +KEY_EMAIL+" TEXT "
                +");";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    // CRUD Persons
    public void addPerson(Person person){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,person.getName());
        values.put(KEY_EMAIL,person.getName());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public int updatePerson(Person person){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,person.getName());
        values.put(KEY_EMAIL,person.getEmail());

        return db.update(TABLE_NAME,values,KEY_ID+" =?",new String[] {String.valueOf(person.getId())});
    }

    public void deletePerson(Person person){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,KEY_ID+" =?",new String[]{String.valueOf(person.getId())});
        db.close();
    }

    public Person getPerson(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{KEY_ID, KEY_NAME, KEY_EMAIL},
                KEY_ID+"=?",
                new String[]{String.valueOf(id)},
                null,null,null,null);
        if(cursor != null)
            cursor.moveToFirst();
        return new Person(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
    }

    public List<Person> getAllPerson(){
        List<Person> lstPersons = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do{
                Person person = new Person();
                person.setId(cursor.getInt(0));
                person.setName(cursor.getString(1));
                person.setEmail(cursor.getString(2));

                lstPersons.add(person);
            }
            while (cursor.moveToNext());
        }
        return lstPersons;
    }


}
