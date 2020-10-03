package com.shunchiangdev.webemployees.repositories;

import com.shunchiangdev.webemployees.models.Employees;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employees, Long> {

}
