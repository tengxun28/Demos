package com.iboxpay.android.mycontentprovider.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by wangyaohui on 16-6-23.
 */
@Table(name = "students")
public class Student extends Model {
    @Column(name = "sid" ,unique = true)
    public long id;

    @Column
    public String name;


    @Column
    public String tel_no;

    @Column
    public int cls_id;

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", tel_no=" + tel_no + ", cls_id=" + cls_id + "]";

    }
}

