package com.example.demo.batch.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Builder;
import lombok.Data;

@Data
@Entity(name = "EMPLOYEE")
@Builder
public class Employee implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMP_SEQ")
    @SequenceGenerator(sequenceName = "employee_seq", allocationSize = 1, name = "EMP_SEQ")
    private String employeeId;
	
	@Column(name = "FIRSTNAME")
    private String firstName;
    
	@Column(name = "LASTNAME")
    private String lastName;
    
	@Column(name = "AGE")
    private int age;
    
	@Column(name = "EMAIL")
    private String email;

}
