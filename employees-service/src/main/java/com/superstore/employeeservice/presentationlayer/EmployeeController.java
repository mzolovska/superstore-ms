package com.superstore.employeeservice.presentationlayer;

import com.superstore.employeeservice.businesslayer.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeResponseModel>> getEmployees(){
        return ResponseEntity.status(HttpStatus.FOUND).body(employeeService.getEmployees());
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseModel> getEmployeeByEmployeeId(@PathVariable String employeeId){
        return ResponseEntity.status(HttpStatus.FOUND).body(employeeService.getEmployeeByEmployeeId(employeeId));
    }

    @PostMapping()
    public ResponseEntity<EmployeeResponseModel> createEmployee(@RequestBody EmployeeRequestModel contactRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployee(contactRequestDTO));
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseModel> updateEmployee(@RequestBody EmployeeRequestModel employeeRequestDTO,
                                                              @PathVariable String employeeId){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployee(employeeRequestDTO, employeeId));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseModel> deleteEmployee(@PathVariable String employeeId){
        employeeService.removeEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}