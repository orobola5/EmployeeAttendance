package com.example.employeeattendance.Model.Repository;

import com.example.employeeattendance.Model.Data.Department;
import com.example.employeeattendance.Model.Data.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface EmployeeRepository extends JpaRepository<Employee,Long> {


    @Query(nativeQuery = true,value = "select * from employees where id =:id ")
    Employee findById(long id);

    @Query(nativeQuery = true,value = "select * from employees")
    List<Employee> findAllEmployee();

    @Query(nativeQuery = true,value = "select * from employees where email =:email ")
    Employee findByEmail(String email);

    @Query(nativeQuery = true, value = "select * from employee where dateJoined between 'from' AND 'to'")
    List<Employee> findByDateJoinedBetween(LocalDate from, LocalDate to);

//    @Query(nativeQuery = true,value = "select * from employees where employId =:employeeId ")
//    Employee findByEmployeeId(long id);
}

