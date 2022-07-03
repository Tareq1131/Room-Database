package com.tareq.android.myapplication.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tareq.android.myapplication.daos.EmployeeDao;
import com.tareq.android.myapplication.entities.Employee;

@Database(entities = {Employee.class}, version = 1)
public abstract class EmployeeDatabase extends RoomDatabase {
    public abstract EmployeeDao getEmployeeDao();

    private static EmployeeDatabase db;

    public static EmployeeDatabase getDb(Context context) {
        if (db == null){
            db = Room.databaseBuilder(context, EmployeeDatabase.class,
                    "employee_db").allowMainThreadQueries()
                    .build();
            return db;
        }
        return db;
    }
}
