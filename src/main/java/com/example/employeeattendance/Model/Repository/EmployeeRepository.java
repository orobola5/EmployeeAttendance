package com.example.employeeattendance.Model.Repository;

import com.example.employeeattendance.Model.Data.Department;
import com.example.employeeattendance.Model.Data.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {


    @Query(nativeQuery = true,value = "select * from employees where id =:id ")
    Employee findById(long id);

    @Query(nativeQuery = true,value = "select * from employees")
    List<Employee> findAllEmployee();
}

