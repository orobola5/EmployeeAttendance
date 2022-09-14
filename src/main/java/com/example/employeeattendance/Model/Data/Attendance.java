package com.example.employeeattendance.Model.Data;

import com.example.employeeattendance.Model.Data.Enum.Availability;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long  employeeId;
    @Column(name="sign_in_time")
    private LocalDateTime signInTime;

    @Column(name="sign_out_time")
    private LocalDateTime signOutTime;

    private Boolean signIn;

    private Availability availability;
}
