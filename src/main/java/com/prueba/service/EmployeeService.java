package com.prueba.service;

import com.prueba.service.dto.EmployeeRequest;
import com.prueba.service.dto.EmployeeResponse;

public interface EmployeeService {
    EmployeeResponse create(EmployeeRequest employeeRequest);
}
