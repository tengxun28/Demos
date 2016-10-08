package com.lava.loaderdemo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

    private MySQLiteOpenHelper helper = null;
    private static final UriMatcher matcher = new UriMatcher(
            UriMatcher.NO_MATCH);
    private static final int students = 1;
    static {
        matcher.addURI("com.app.contentprovider", "tb_student", students);
    }

    @Override
    public int delete(Uri arg0, String arg1, String[] arg2) {
        return 0;
    }

    @Override
    public String getType(Uri arg0) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int flag = matcher.match(uri);
        switch (flag) {
            case students:
                long id = db.insert("tb_student", null, values);
                return ContentUris.withAppendedId(uri, id);
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        helper = new MySQLiteOpenHelper(this.getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor=db.query("tb_student", projection, selection, selectionArgs, null, null, null);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }

}