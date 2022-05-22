package com.example.Employee.Exception;

public class EmployeeNotFoundException extends Exception{
	private int Id;
	public EmployeeNotFoundException(int Id) {
		super(String.format("Employee not founf with id: '%s'",Id));
	}

}
