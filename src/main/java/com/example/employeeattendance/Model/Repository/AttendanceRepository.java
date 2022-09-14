package com.example.employeeattendance.Model.Repository;

import com.example.employeeattendance.Model.Data.Attendance;
import com.example.employeeattendance.Model.Data.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
}
