package com.example.employeeattendance.Model.Repository;

import com.example.employeeattendance.Model.Data.Department;
import com.example.employeeattendance.Model.Data.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

}


