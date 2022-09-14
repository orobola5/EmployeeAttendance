package com.example.employeeattendance.Service;

import com.example.employeeattendance.Dto.Response.DepartmentResponse;
import com.example.employeeattendance.Model.Data.Department;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {
    Department findDepartmentByName(String name);

    DepartmentResponse findAllDepartment();

    void save(Department department1);

    Department findByName(String department);
}
