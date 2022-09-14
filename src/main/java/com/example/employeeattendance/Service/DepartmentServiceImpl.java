package com.example.employeeattendance.Service;

import com.example.employeeattendance.Dto.Response.DepartmentResponse;
import com.example.employeeattendance.Exception.ResourceNotFoundException;
import com.example.employeeattendance.Model.Data.Department;
import com.example.employeeattendance.Model.Repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;
    @Override
    public Department findDepartmentByName(String name) {

        Department department = departmentRepository.findByName(name);
        if (department == null){
            throw new ResourceNotFoundException("department not found");
        }
        return department;
    }

    @Override
    public DepartmentResponse findAllDepartment() {
        List<Department> departments = departmentRepository.findAllDepartments();
        DepartmentResponse response = new DepartmentResponse();
        response.setDepartments(departments);
        return  response;
    }
}
