package com.example.Employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Employee.Exception.EmployeeNotFoundException;
import com.example.Employee.Repository.EmployeeRepository;
import com.example.Employee.model.Employee;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
	EmployeeRepository employeeRepository;
	
	//Get all employees
	@GetMapping("/employees")
	public List<Employee> displayMessage(){
		return employeeRepository.findAll();
		
	}
	
	//Save employees
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee emp) {
		Employee getEmp = employeeRepository.save(emp);
		return getEmp;
		}
	//save employee by id
	
	@GetMapping("/employees/{Id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable Integer Id){
	Employee getEmp = employeeRepository.getById(Id);
		return ResponseEntity.ok().body(getEmp);
	}
	
	//update an employee by id
	
	@PutMapping("/employees/{Id}")
	  public ResponseEntity<Employee> updatedEmployeeById(@PathVariable(value="Id") Integer Id,
			  @Validated @RequestBody Employee Employee)throws EmployeeNotFoundException{
		Employee getEmp = employeeRepository.findById(Id)
				.orElseThrow(()->new EmployeeNotFoundException(Id));
		getEmp.setid(Employee.getid());
		getEmp.setName(Employee.getName());
		getEmp.setSalary(Employee.getSalary());
		getEmp.setDepartment(Employee.getDepartment());
		
		Employee updateEmp = employeeRepository.save(getEmp);
		
		return ResponseEntity.ok().body(updateEmp);
	}
	@DeleteMapping("/employees/{Id}")
	public String deleteEmployeeById(@PathVariable Integer Id){
		Employee getEmp = employeeRepository.getById(Id);
		employeeRepository.delete(getEmp);
		return "A record successfully deleted";
	}
}