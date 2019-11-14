package com.example.demo.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.batch.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>
{

}
