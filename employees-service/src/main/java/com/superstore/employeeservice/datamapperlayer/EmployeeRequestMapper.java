package com.superstore.employeeservice.datamapperlayer;

import com.superstore.employeeservice.datalayer.Employee;
import com.superstore.employeeservice.datalayer.EmployeeIdentifier;
import com.superstore.employeeservice.presentationlayer.EmployeeRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EmployeeRequestMapper {

    @Mapping(target = "id", ignore = true)
    Employee requestModelToEntity(EmployeeRequestModel employeeRequestDTO, EmployeeIdentifier employeeIdentifier);
}
