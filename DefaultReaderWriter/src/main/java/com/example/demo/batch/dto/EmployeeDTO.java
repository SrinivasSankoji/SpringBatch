package com.example.demo.batch.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private int age;

}
