package com.tareq.android.myapplication.callback;

import com.tareq.android.myapplication.entities.Employee;

public interface EmployeeActionListener {

    void onEditEmployee(Employee employee);
    void onDeleteEmployee(Employee employee);
}
