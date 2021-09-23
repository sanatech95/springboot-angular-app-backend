package com.springrest.springrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springrest.springrest.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
