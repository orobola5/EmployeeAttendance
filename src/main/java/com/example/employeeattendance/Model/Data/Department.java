package com.example.employeeattendance.Model.Data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Setter
@Getter
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "department_name")
    private String departmentName;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
