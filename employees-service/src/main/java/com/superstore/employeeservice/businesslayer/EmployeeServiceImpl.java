package com.superstore.employeeservice.businesslayer;

import com.superstore.employeeservice.datalayer.Employee;
import com.superstore.employeeservice.datalayer.EmployeeIdentifier;
import com.superstore.employeeservice.datalayer.EmployeeRepository;
import com.superstore.employeeservice.datamapperlayer.EmployeeRequestMapper;
import com.superstore.employeeservice.datamapperlayer.EmployeeResponseMapper;
import com.superstore.employeeservice.presentationlayer.EmployeeRequestModel;
import com.superstore.employeeservice.presentationlayer.EmployeeResponseModel;
import com.superstore.employeeservice.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeRequestMapper employeeRequestMapper;
    private EmployeeResponseMapper employeeResponseMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeRequestMapper employeeRequestMapper, EmployeeResponseMapper employeeResponseMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeRequestMapper = employeeRequestMapper;
        this.employeeResponseMapper = employeeResponseMapper;
    }

    @Override
    public List<EmployeeResponseModel> getEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();

        return employeeResponseMapper.entityListToResponseDTOList(employeeList);    }

    @Override
    public EmployeeResponseModel getEmployeeByEmployeeId(String employeeId) {

        Employee employee = employeeRepository.findByEmployeeIdentifier_EmployeeId(employeeId);
        if(employee == null){
            throw new NotFoundException("Unknown employeeId: "+employeeId);
        }
        return employeeResponseMapper.entityToResponseDTO(employee);
    }

    @Override
    public EmployeeResponseModel addEmployee(EmployeeRequestModel employeeRequestModel) {
        Employee employee = employeeRequestMapper.requestModelToEntity(employeeRequestModel, new EmployeeIdentifier());

        return employeeResponseMapper.entityToResponseDTO(employeeRepository.save(employee));}

    @Override
    public EmployeeResponseModel updateEmployee(EmployeeRequestModel employeeRequestModel, String employeeId) {
        Employee foundEmployee = employeeRepository.findByEmployeeIdentifier_EmployeeId(employeeId);


        if (foundEmployee == null) {
            throw new NotFoundException("The employeeid is not found: "+employeeId);
        }

        Employee employee = employeeRequestMapper.requestModelToEntity(employeeRequestModel, foundEmployee.getEmployeeIdentifier());
        employee.setId(foundEmployee.getId());
        return employeeResponseMapper.entityToResponseDTO(employeeRepository.save(employee));
    }

    @Override
    public void removeEmployee(String employeeId) {

        Employee existingEmployee = employeeRepository.findByEmployeeIdentifier_EmployeeId(employeeId.toString());

        if (existingEmployee == null) {
            throw new NotFoundException("The employeeid is not found: "+employeeId);
        }

        employeeRepository.delete(existingEmployee);
    }
}
