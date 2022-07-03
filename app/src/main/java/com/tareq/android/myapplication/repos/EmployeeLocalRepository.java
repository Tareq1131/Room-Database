package com.tareq.android.myapplication.repos;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.tareq.android.myapplication.daos.EmployeeDao;
import com.tareq.android.myapplication.db.EmployeeDatabase;
import com.tareq.android.myapplication.entities.Employee;

import java.util.List;

public class EmployeeLocalRepository {
    private EmployeeDao employeeDao;

    public EmployeeLocalRepository(Context context) {
        employeeDao = EmployeeDatabase.getDb(context).getEmployeeDao();
    }

    public void addEmployee(Employee employee){
        employeeDao.insertEmployee(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
    }

    public void  deleteEmployee(Employee employee){
        employeeDao.deleteEmployee(employee);
    }

    public LiveData<List<Employee>> getEmployees() {
        return employeeDao.getAllEmployees();
    }
}
