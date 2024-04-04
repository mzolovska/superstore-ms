package com.superstore.employeeservice.businesslayer;


import com.superstore.employeeservice.presentationlayer.EmployeeRequestModel;
import com.superstore.employeeservice.presentationlayer.EmployeeResponseModel;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponseModel> getEmployees();
    EmployeeResponseModel getEmployeeByEmployeeId(String employeeId);
    EmployeeResponseModel addEmployee(EmployeeRequestModel employeeRequestModel);
    EmployeeResponseModel updateEmployee(EmployeeRequestModel employeeRequestModel, String employeeId);
    void removeEmployee(String employeeId);
}
