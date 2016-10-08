package com.iboxpay.android.mycontentprovider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.iboxpay.android.mycontentprovider.database.Student;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private Student mockStudent(int i) {
        Student student = new Student();
        student.id = i;
        student.name = "user - " + i;
        student.tel_no = String.valueOf(new Random().nextInt(200000));
        student.cls_id = new Random().nextInt(5);

        return  student;
    }

    public void add(View v) {
        Log.e("xx","add");
        for(int i = 0; i < 5; i++) {
            mockStudent(i).save();
        }
    }

    public void update(View v) {
        Log.e("xx","update");
        new Update(Student.class).set("name=?", "Mr.simple").where("sid=?",1).execute();
    }

    public void delete(View v) {
        Log.e("xx","delete");
        new Delete().from(Student.class).where("sid=?",2).execute();
    }

    public void query(View v) {
        Log.e("xx","queue");
        List<Student> result = new Select().from(Student.class).where("sid > ? and cls_id=2","1").execute();

        for(Student student: result) {
            Log.e("xx", student.toString());
        }
    }


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.e("xx","onTrimMemory: " + level);
    }
}
