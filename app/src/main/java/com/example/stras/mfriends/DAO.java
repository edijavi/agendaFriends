package com.example.stras.mfriends;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DAO {

    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "friend_table";
    private static final String[] COLUMNS = new String[]{"id", "first_name", "last_name", "address", "website", "email", "phone"};

    private static Context context;

    private SQLiteDatabase db;
    private SQLiteStatement insertStmt;

    private static DAO m_instance;

    public static void setContext(Context c) {
        context = c;
    }

    public static DAO getInstance() {
        if (m_instance == null) m_instance = new DAO(context);
        return m_instance;
    }

    private DAO(Context context) {
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStmt = this.db.compileStatement(INSERT);
    }

    private static final String INSERT = "insert into " + TABLE_NAME +
            "(first_name,last_name,address,website,email,phone)" + " values (?,?,?,?,?,?)";

    public long insert(BEFriend friend) {
        this.insertStmt.bindString(1, friend.getFirstName());
        this.insertStmt.bindString(2, friend.getLastName());
        this.insertStmt.bindString(3, friend.getAddress());
        this.insertStmt.bindString(4, friend.getWebsite());
        this.insertStmt.bindString(5, friend.getEmail());
        this.insertStmt.bindString(6, friend.getPhone());
        long id = this.insertStmt.executeInsert();
        friend.setId(id);
        return id;
    }

    public void deleteAll() {

        this.db.delete(TABLE_NAME, null, null);
    }

    public void deleteById(long id) {
        this.db.delete(TABLE_NAME, "id = " + id, null);
    }

    public void update(BEFriend friend) {
    }

    public ArrayList<BEFriend> getAll() {
        ArrayList<BEFriend> list = new ArrayList<>();
        Cursor cursor = this.db.query(TABLE_NAME, COLUMNS, null, null, null, null, "first_name desc");
        if (cursor.moveToFirst()) {
            do {
                list.add(new BEFriend(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6)));
            } while (cursor.moveToNext());
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        return list;
    }

    public BEFriend getById(long id) {

        String[] whereArgs = {Long.toString(id)};
        Cursor cursor = this.db.query(TABLE_NAME, COLUMNS, "id = ?", whereArgs, null, null, null);
        BEFriend friend = null;
        try {
            if (cursor.moveToFirst()) {

                friend = new BEFriend(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6));
            }
        } finally {
            cursor.close();
        }
        return friend;
    }


    private static class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, first_name TEXT, last_name TEXT, address TEXT, website TEXT, email TEXT, phone TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
