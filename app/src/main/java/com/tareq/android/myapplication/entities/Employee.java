package com.tareq.android.myapplication.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tbl_employee")
public class Employee implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "emp_name")
    private String empName;
    @ColumnInfo(name = "emp_salary")
    private double empSalary;

    public Employee(String empName, double empSalary) {
        this.empName = empName;
        this.empSalary = empSalary;
    }

    @Ignore
    public Employee(int id, String empName, double empSalary) {
        this.id = id;
        this.empName = empName;
        this.empSalary = empSalary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public double getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(double empSalary) {
        this.empSalary = empSalary;
    }
}
