package com.superstore.employeeservice.presentationlayer;

import com.superstore.employeeservice.datalayer.Department;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeResponseModel  {

    private final String employeeId;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final String phoneNumber;

    private final String salary;
    private final String commissionRate;

    private final Department department;
    private final String streetAddress;
    private final String city;
    private final String province;
    private final String country;
    private final String postalCode;
}
