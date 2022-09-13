package com.example.employeeattendance.Dto.Response;

import com.example.employeeattendance.Model.Data.Department;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentResponse {
    private List<Department> departments;
}
