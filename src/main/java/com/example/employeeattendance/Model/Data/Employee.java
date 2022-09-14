package com.example.employeeattendance.Model.Data;

import com.example.employeeattendance.Model.Data.Enum.Availability;
import com.example.employeeattendance.Model.Data.Enum.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "address")
    private String address;
    @Column(name="email", unique = true)
    private String email;
    @Column(name="password")
    private String password;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @Column(name = "date")
    private LocalDateTime localDateTime;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Attendance attendance;

}