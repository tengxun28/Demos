package com.lava.loaderdemo;

//import android.app.LoaderManager;
//import android.content.CursorLoader;
//import android.content.Loader;
//import android.net.Uri;
//import android.provider.ContactsContract;
//import android.provider.ContactsContract.Contacts;
//import android.support.v4.widget.SimpleCursorAdapter;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.widget.ListView;
//
//public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Object> {
//
//    SimpleCursorAdapter mAdapter;
//    String mCurFilter;
//
//
//    // 这是我们想获取的联系人中一行的数据．
//    static final String[] CONTACTS_SUMMARY_PROJECTION = new String[] {
//            Contacts._ID,
//            Contacts.DISPLAY_NAME,
//            Contacts.CONTACT_STATUS,
//            Contacts.CONTACT_PRESENCE,
//            Contacts.PHOTO_ID,
//            ContactsContract.Contacts.LOOKUP_KEY,
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        mAdapter = new SimpleCursorAdapter(this,
//                android.R.layout.simple_list_item_2, null,
//                new String[] { ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.CONTACT_STATUS },
//                new int[] { android.R.id.text1, android.R.id.text2 }, 0);
//
//        ListView lv = (ListView)findViewById(R.id.list);
//        lv.setAdapter(mAdapter);
//
//        getLoaderManager().initLoader(0,null, this);
//    }
//
//    @Override
//    public Loader<Object> onCreateLoader(int i, Bundle bundle) {
//        // 当一个新的loader需被创建时调用．本例仅有一个Loader，
//        //所以我们不需关心ID．首先设置base URI，URI指向的是联系人
//        Uri baseUri;
//        if (mCurFilter != null) {
//            baseUri = Uri.withAppendedPath(Contacts.CONTENT_FILTER_URI,
//                    Uri.encode(mCurFilter));
//        } else {
//            baseUri = Contacts.CONTENT_URI;
//        }
//
//        // 现在创建并返回一个CursorLoader，它将负责创建一个
//        // Cursor用于显示数据
//        String select = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND ("
//                + Contacts.HAS_PHONE_NUMBER + "=1) AND ("
//                + Contacts.DISPLAY_NAME + " != '' ))";
////        return new (Loader<Object>)CursorLoader(MainActivity.this, baseUri,
////                CONTACTS_SUMMARY_PROJECTION, select, null,
////                Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC");
//        return  null;
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Object> loader, Object o) {
//
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Object> loader) {
//
//    }
//}


import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    LoaderManager manager = null;
    ListView listView = null;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) this.findViewById(R.id.list);
        manager = this.getLoaderManager();
        manager.initLoader(1000, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        CursorLoader loader = new CursorLoader(this,
                Uri.parse("content://com.app.contentprovider"), null, null,
                null, null);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        ArrayList<String> al = new ArrayList<String>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            al.add(name);
        }
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,al);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
