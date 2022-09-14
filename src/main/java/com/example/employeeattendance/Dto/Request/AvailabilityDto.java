package com.example.employeeattendance.Dto.Request;

import com.example.employeeattendance.Model.Data.Enum.Availability;
import lombok.Data;

@Data
public class AvailabilityDto {
    private Availability availability;
}
