package com.superstore.employeeservice.datamapperlayer;

import com.superstore.employeeservice.datalayer.Employee;
import com.superstore.employeeservice.presentationlayer.EmployeeResponseModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface EmployeeResponseMapper {

    @Mapping(expression = "java(employee.getEmployeeIdentifier().getEmployeeId())", target = "employeeId")
    EmployeeResponseModel entityToResponseDTO(Employee employee);

    List<EmployeeResponseModel> entityListToResponseDTOList(List<Employee> employeeList);

    /*@AfterMapping
    default void addLinks(EmployeeResponseModel model, Employee employee){
        //self link
        model.add(linkTo(methodOn(EmployeeController.class).
                getEmployeeByEmployeeId(model.getEmployeeId())).
                withSelfRel());

        //all employees link
        model.add(linkTo(methodOn(EmployeeController.class).getEmployees())
                .withRel("employees"));
    }*/
}
