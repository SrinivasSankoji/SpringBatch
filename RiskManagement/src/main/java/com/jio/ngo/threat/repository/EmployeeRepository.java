package com.jio.ngo.threat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jio.ngo.threat.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>
{

}
