package com.example.employeeattendance.Model.Repository;

import com.example.employeeattendance.Model.Data.Department;
import com.example.employeeattendance.Model.Data.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    @Query(nativeQuery = true,value = "select * from departments where department_name = :department")
    Department findByName(String department);

    @Query(nativeQuery = true,value = "select * from departments")
    List<Department> findAllDepartments();
}


